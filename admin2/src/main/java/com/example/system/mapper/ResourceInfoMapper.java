package com.example.system.mapper;

import com.example.system.entity.ResourceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.system.vo.ResourceInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
public interface ResourceInfoMapper extends BaseMapper<ResourceInfo> {

    List<ResourceInfoVo> listByTable();

    List<ResourceInfo> listByUserId(@Param("userId") Integer userId);
}
