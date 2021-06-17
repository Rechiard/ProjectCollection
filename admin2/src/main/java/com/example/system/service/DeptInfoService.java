package com.example.system.service;

import com.example.frame.dto.ResponseResult;
import com.example.system.entity.DeptInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.system.vo.DeptInfoVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
public interface DeptInfoService extends IService<DeptInfo> {

    List<DeptInfoVo> listByTable(DeptInfoVo entity);

    ResponseResult saveEntity(DeptInfo entity);

    ResponseResult updateEntity(DeptInfo entity);

    Map<String, Object> listByDTree();
}
