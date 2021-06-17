package com.example.system.service.impl;

import com.example.system.entity.SystemLog;
import com.example.system.mapper.SystemLogMapper;
import com.example.system.service.SystemLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements SystemLogService {

}
