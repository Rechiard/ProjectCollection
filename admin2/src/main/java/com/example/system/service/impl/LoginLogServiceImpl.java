package com.example.system.service.impl;

import com.example.system.entity.LoginLog;
import com.example.system.mapper.LoginLogMapper;
import com.example.system.service.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
