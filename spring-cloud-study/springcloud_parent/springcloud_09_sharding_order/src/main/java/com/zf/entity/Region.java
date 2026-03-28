package com.zf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 区域字典实体
 * 分库分表改造提示：
 * 1. Region表适合作为广播表（Broadcast Table）
 * 2. 广播表：在每个分库中都存在，数据完全相同
 * 3. 适合作为广播表的场景：数据量小、变更频率低、需要关联查询的字典表
 */
@Data
@TableName("t_region")
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域ID - 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 区域代码（如：110000-北京市）
     */
    private String code;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 父级区域ID
     */
    private Long parentId;

    /**
     * 区域级别：1-省/直辖市 2-市 3-区/县
     */
    private Integer level;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除：0-未删除 1-已删除
     */
    private Integer deleted;
}