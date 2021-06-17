package com.example.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.frame.dto.ResponseResult;
import com.example.frame.dto.layuimini.CheckArr;
import com.example.frame.dto.layuimini.DTree;
import com.example.system.entity.DeptInfo;
import com.example.system.mapper.DeptInfoMapper;
import com.example.system.service.DeptInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.system.vo.DeptInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
@Service
public class DeptInfoServiceImpl extends ServiceImpl<DeptInfoMapper, DeptInfo> implements DeptInfoService {

    @Override
    public List<DeptInfoVo> listByTable(DeptInfoVo entity) {
        return baseMapper.listByTable(entity);
    }

    @Override
    public ResponseResult saveEntity(DeptInfo entity) {
        List<DeptInfo> existList = baseMapper.selectList(new LambdaQueryWrapper<DeptInfo>()
                .eq(DeptInfo::getName,entity.getName()));
        if(!CollectionUtils.isEmpty(existList)){
            return ResponseResult.error("部门已存在",entity.getName());
        }
        baseMapper.insert(entity);
        return ResponseResult.success("新增成功");
    }

    @Override
    public ResponseResult updateEntity(DeptInfo entity) {
        List<DeptInfo> existList = baseMapper.selectList(new LambdaQueryWrapper<DeptInfo>()
                .eq(DeptInfo::getName,entity.getName()));
        if(!CollectionUtils.isEmpty(existList) && existList.size() > 1){
            return ResponseResult.error("数据库存在多个部门名称",entity.getName());
        }else if(existList.size() == 1 && !existList.get(0).getId().equals(entity.getId())){
            return ResponseResult.error("部门名称已存在",entity.getName());
        }else{
            baseMapper.updateById(entity);
        }
        return ResponseResult.success("更新成功");
    }

    @Override
    public Map<String, Object> listByDTree() {
        List<DeptInfo> list = baseMapper.selectList(new LambdaQueryWrapper<DeptInfo>()
                .orderByAsc(DeptInfo::getSort));
        list.forEach(System.out::println);
        List<DTree> resList = dTreeList(list,0);
        return ResponseResult.dTree(resList);
    }

    //利用递归将DeptInfo的数据根据DTree的数据类型输入到列表中
    public List<DTree> dTreeList(List<DeptInfo> list,int parentId){
        List<DTree> resList = new ArrayList<>();
        for(DeptInfo res : list){
            //判断父级id和当前id的关系
            if(res.getPid().equals(parentId)){
                resList.add(new DTree()
                        .setId(res.getId())
                        .setTitle(res.getName())
                        .setSpread(true)
                        .setParentId(res.getPid())
                        .setCheckArr(Arrays.asList(new CheckArr().setType(0).setChecked(0)))
                        .setChildren(dTreeList(list,res.getId()))
                );
            }
        }
        return resList;
    }
}
