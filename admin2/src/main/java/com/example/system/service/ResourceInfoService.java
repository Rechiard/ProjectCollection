package com.example.system.service;

import com.example.frame.dto.layuimini.MenuInfo;
import com.example.system.entity.ResourceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.system.vo.ResourceInfoVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
public interface ResourceInfoService extends IService<ResourceInfo> {

    List<ResourceInfoVo> listByTable();

    List<MenuInfo> listAllByMenu();

    Map<String, Object> listByDTree();
}
