package com.example.system.service;

import com.example.frame.dto.ResponseResult;
import com.example.system.entity.RoleInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.system.vo.RoleInfoVo;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
public interface RoleInfoService extends IService<RoleInfo> {

    ResponseResult listByTable(RoleInfoVo entity);

    ResponseResult saveEntity(RoleInfoVo entity);

    ResponseResult updateEntity(RoleInfoVo entity);
}
