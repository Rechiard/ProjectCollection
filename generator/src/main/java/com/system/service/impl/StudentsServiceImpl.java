package com.system.service.impl;

import com.system.entity.Students;
import com.system.mapper.StudentsMapper;
import com.system.service.StudentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shuxiangxiaoyuan
 * @since 2022-07-15
 */
@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students> implements StudentsService {

}
