package com.shawn.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.shawn.service.AlyOssService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: OSS接口
 */
@Slf4j
public class AlyOssServiceImpl implements AlyOssService {

    private OSS ossClient;
    // 分片上传每片的大小
    private Long partSize = 1 * 1024 * 1024l;

    private void cancel(String uploadId, String bucketName, String objectName) {
        try {
            // 取消分片上传。
            AbortMultipartUploadRequest abortMultipartUploadRequest =
                    new AbortMultipartUploadRequest(bucketName, objectName, uploadId);
            ossClient.abortMultipartUpload(abortMultipartUploadRequest);
        } catch (OSSException e) {
            log.error("取消上传失败: uploadId:{},bucketName:{}, objectName:{}, {}",
                    uploadId, bucketName, objectName, e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void list(String bucketName, String objectName, String uploadId) {
        try {
            // 列举已上传的分片。
            ListPartsRequest listPartsRequest = new ListPartsRequest(bucketName, objectName, uploadId);
            // 设置uploadId。
            // listPartsRequest.setUploadId(uploadId);
            // 设置分页时每一页中分片数量为100个。默认列举1000个分片。
            listPartsRequest.setMaxParts(100);
            // 指定List的起始位置。只有分片号大于此参数值的分片会被列举。
            listPartsRequest.setPartNumberMarker(2);
            PartListing partListing = ossClient.listParts(listPartsRequest);

            for (PartSummary part : partListing.getParts()) {
                // 获取分片号。
                System.out.println(part.getPartNumber());
                // 获取分片数据大小。
                System.out.println(part.getSize());
                // 获取分片的ETag。
                System.out.println(part.getETag());
                // 获取分片的最后修改时间。
                System.out.println(part.getLastModified());
            }
        } catch (OSSException e) {
            log.error("列举分片失败: uploadId:{},bucketName:{}, objectName:{}, {}",
                    uploadId, bucketName, objectName, e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private void listEvent(String bucketName) {
        try {
            // 列举分片上传事件。默认列举1000个分片。
            ListMultipartUploadsRequest listMultipartUploadsRequest = new ListMultipartUploadsRequest(bucketName);
            MultipartUploadListing multipartUploadListing = ossClient.listMultipartUploads(listMultipartUploadsRequest);

            for (MultipartUpload multipartUpload : multipartUploadListing.getMultipartUploads()) {
                log.info("uploadId: {}, key: {}, time: {}",
                        multipartUpload.getUploadId(), multipartUpload.getKey(), multipartUpload.getInitiated());
            }
        } catch (OSSException e) {
            log.error("取消上传失败: bucketName:{}, {}",
                    bucketName, e.getLocalizedMessage());
        }
    }

    @Override
    public void upload(String bucketName, String objectName, String path) {
        // 创建InitiateMultipartUploadRequest对象。
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, objectName);
        // 初始化分片。
        InitiateMultipartUploadResult upResult = ossClient.initiateMultipartUpload(request);
        String uploadId = upResult.getUploadId();
        multipartUpload(bucketName, objectName, path, null, null, uploadId);
    }

    public void continueUpload(String bucketName, String objectName, String path,
                               Integer startIndex, Integer endIndex, String uploadId) {
        log.info("续传：uploadId:{}, bucketName:{}, objectName:{}, startIndex:{},endIndex:{}",
                uploadId, bucketName, objectName, startIndex, endIndex);
        multipartUpload(bucketName, objectName, path, startIndex, endIndex, uploadId);
    }

    private void multipartUpload(String bucketName, String objectName, String path,
                                 Integer startIndex, Integer endIndex, String uploadId) {
        long startTime = System.currentTimeMillis();
        // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
        List<PartETag> partETags = new ArrayList<>();

        URL url;
        long fileLength;
        try {
            url = new URL(path);
            URLConnection urlConnection = url.openConnection();
            fileLength = urlConnection.getContentLengthLong();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UploadPartRequest uploadPartRequest = new UploadPartRequest();
        uploadPartRequest.setBucketName(bucketName);
        uploadPartRequest.setKey(objectName);
        uploadPartRequest.setUploadId(uploadId);
        // 遍历分片上传
        int i = startIndex == null ? 0 : startIndex;
        int partCount = calcPartCount(fileLength, partSize, endIndex);
        log.info("开始分片上传，共{}份文件，uploadId:{}", partCount, uploadId);
        for (; i < partCount; i++) {
            long curStartTime = System.currentTimeMillis();
            long startPos = (long) i * partSize;
            // 如果是最后一个分片则为剩下所有的大小，否则则是每次分片的大小
            long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
            InputStream inputStream;
            try {
                // 因为每次分片上传后都会close inputStream，所以每次上传前需要重新open
                URLConnection urlConnection = url.openConnection();
                inputStream = urlConnection.getInputStream();
                // InputStream.skip()方法跳过已经上传的数据
                long skip = inputStream.skip(startPos);
                log.info("第{}份文件开始上传, 共{}份文件, 跳过了{}字节数据", partCount, i, skip);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            uploadPartRequest.setInputStream(inputStream);
            // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100 KB。
            uploadPartRequest.setPartSize(curPartSize);
            // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出此范围，OSS将返回InvalidArgument错误码。
            uploadPartRequest.setPartNumber(i + 1);
            // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
            UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
            // 每次上传分片之后，OSS的返回结果包含PartETag。PartETag将被保存在partETags中。
            partETags.add(uploadPartResult.getPartETag());
            log.info("第{}份文件上传成功，共{}份文件，耗时：{}秒",
                    i, partCount, (System.currentTimeMillis() - curStartTime) / 1000);
        }

        // 创建 CompleteMultipartUploadRequest 对象。
        // 在执行完成分片上传操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。
        // 当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETags);

        CompleteMultipartUploadResult completeMultipartUploadResult = ossClient
                .completeMultipartUpload(completeMultipartUploadRequest);

        log.info("文件合并完成，bucketName:{}, objectName:{}, fileSize:{}, eTag: {}, 共耗时: {}秒",
                bucketName,
                objectName,
                fileLength,
                completeMultipartUploadResult.getETag(),
                (System.currentTimeMillis() - startTime) / 1000);
    }

    private int calcPartCount(long fileLength, long partSize, Integer endIndex) {
        int partCount = endIndex == null ? (int) (fileLength / partSize) : endIndex;
        if (fileLength % partSize != 0) {
            partCount++;
        }
        return partCount;
    }
}