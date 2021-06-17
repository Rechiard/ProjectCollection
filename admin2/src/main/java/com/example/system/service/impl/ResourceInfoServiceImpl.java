package com.example.system.service.impl;

import com.example.frame.dto.layuimini.MenuInfo;
import com.example.system.entity.ResourceInfo;
import com.example.system.mapper.ResourceInfoMapper;
import com.example.system.service.ResourceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.system.vo.ResourceInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
@Service
public class ResourceInfoServiceImpl extends ServiceImpl<ResourceInfoMapper, ResourceInfo> implements ResourceInfoService {

    @Override
    public List<ResourceInfoVo> listByTable() {
        return null;
    }

    @Override
    public List<MenuInfo> listAllByMenu() {
        return null;
    }

    @Override
    public Map<String, Object> listByDTree() {
        return null;
    }
}
