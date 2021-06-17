package com.example.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.frame.dto.ResponseResult;
import com.example.system.entity.MapRoleResource;
import com.example.system.entity.RoleInfo;
import com.example.system.mapper.MapRoleResourceMapper;
import com.example.system.mapper.RoleInfoMapper;
import com.example.system.service.MapRoleResourceService;
import com.example.system.service.RoleInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.system.vo.RoleInfoVo;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements RoleInfoService {

    @Autowired
    private MapRoleResourceService mapRoleResourceService;
    @Autowired
    private MapRoleResourceMapper mapRoleResourceMapper;

    @Override
    public ResponseResult listByTable(RoleInfoVo entity) {
        List<RoleInfoVo> list = baseMapper.listByTable(entity);

        System.out.println("============我是分界线==========");
        list.forEach(System.out::println);
        System.out.println("============我是分界线==========");
        //给每个角色寻找它的权限
        list.forEach(item->{
            List<MapRoleResource> mapRoleResources = mapRoleResourceMapper.selectList(
                    new LambdaQueryWrapper<MapRoleResource>().eq(MapRoleResource::getRoleId,item.getId()));
            List<Integer> resourcesIds = mapRoleResources.stream().map(MapRoleResource::getResourceId).collect(Collectors.toList());
            //角色权限用 ',' 拼接到一起
            item.setResourceIds(Joiner.on(",").join(resourcesIds));
        });
        return ResponseResult.table(baseMapper.countByTable(entity),list);
    }

    @Override
    public ResponseResult saveEntity(RoleInfoVo entity) {
        List<RoleInfo> existList = baseMapper.selectList(new LambdaQueryWrapper<RoleInfo>()
                .eq(RoleInfo::getName, entity.getName()));
        if (!CollectionUtils.isEmpty(existList)) {
            return ResponseResult.error("角色名称已存在");
        }
        baseMapper.insert(entity);
        return ResponseResult.success("新增成功");
    }

    @Override
    public ResponseResult updateEntity(RoleInfoVo entity) {
        List<RoleInfo> existList = baseMapper.selectList(new LambdaQueryWrapper<RoleInfo>()
                .eq(RoleInfo::getName, entity.getName()));
        if (!CollectionUtils.isEmpty(existList) && existList.size() > 1) {
            return ResponseResult.error("数据库存在多个角色名称");
        } else if (existList.size() == 1 && !existList.get(0).getId().equals(entity.getId())) {
            return ResponseResult.error("角色名称已存在");
        } else {
            baseMapper.updateById(entity);
            mapRoleResourceMapper.delete(new LambdaQueryWrapper<MapRoleResource>()
                    .eq(MapRoleResource::getRoleId, entity.getId()));
            saveBatchMapRoleResource(entity);
        }
        return ResponseResult.success("更新成功");
    }

    private void saveBatchMapRoleResource(RoleInfoVo entity) {
        if (StringUtils.isNotEmpty(entity.getResourceIds())) {
            List<MapRoleResource> list = new ArrayList<>();
            String[] split = entity.getResourceIds().split(",");
            for (int i = 0; i < split.length; i++) {
                list.add(new MapRoleResource().setRoleId(entity.getId()).setResourceId(Integer.valueOf(split[i])));
            }
            mapRoleResourceService.saveBatch(list, list.size());
        }
    }
}
