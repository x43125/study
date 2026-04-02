package com.zf.mapper;

import com.zf.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 区域字典Mapper
 */
@Mapper
public interface RegionMapper {

    /**
     * 插入区域
     */
    int insert(Region region);

    /**
     * 根据ID查询区域
     */
    Region selectById(@Param("id") Long id);

    /**
     * 查询所有区域
     */
    List<Region> selectAll();

    /**
     * 统计区域数量
     */
    long selectCount();

    /**
     * 根据ID更新区域
     */
    int updateById(Region region);
}
