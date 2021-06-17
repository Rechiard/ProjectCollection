package com.example.system.service;

import com.example.frame.dto.ResponseResult;
import com.example.system.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.system.vo.UserInfoVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
public interface UserInfoService extends IService<UserInfo> {

    ResponseResult listByTable(UserInfoVo entity);

    ResponseResult saveEntity(UserInfoVo entity);

    ResponseResult resetPwd(UserInfoVo entity);

    ResponseResult updateEntity(UserInfoVo entity);

    UserInfoVo getByName(String userName);

    ResponseResult updateInfoOrPwdById(UserInfoVo entity);
}
