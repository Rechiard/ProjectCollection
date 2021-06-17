package com.example.system.mapper;

import com.example.frame.dto.ResponseResult;
import com.example.system.entity.DeptInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.system.vo.DeptInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
public interface DeptInfoMapper extends BaseMapper<DeptInfo> {

    List<DeptInfoVo> listByTable(@Param("entity") DeptInfoVo entity);

    ResponseResult saveEntity(DeptInfo entity);

    ResponseResult updateEntity(DeptInfo entity);

    Map<String, Object> listByDTree();
}
