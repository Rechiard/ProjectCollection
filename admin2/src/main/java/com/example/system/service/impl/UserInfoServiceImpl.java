package com.example.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.frame.consts.SessionConst;
import com.example.frame.dto.ResponseResult;
import com.example.frame.shiro.util.ShiroUtils;
import com.example.system.entity.MapUserRole;
import com.example.system.entity.UserInfo;
import com.example.system.mapper.MapUserRoleMapper;
import com.example.system.mapper.UserInfoMapper;
import com.example.system.service.MapUserRoleService;
import com.example.system.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.system.vo.UserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private MapUserRoleService mapUserRoleService;
    @Autowired
    private MapUserRoleMapper mapUserRoleMapper;

    @Override
    public ResponseResult listByTable(UserInfoVo entity) {
        return ResponseResult.table(baseMapper.countByTable(entity),baseMapper.listByTable(entity));
    }

    @Override
    public ResponseResult saveEntity(UserInfoVo entity) {
        //查询user_info表中的所有name 是否和 添加的entity的name 有相同的
        List<UserInfo> existList = baseMapper.selectList(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getName,entity.getName()));
        //如果有则返回error
        if(!CollectionUtils.isEmpty(existList)){
            return ResponseResult.error("用户名已存在: "+ entity.getName());
        }
        entity.setSalt(ShiroUtils.getRandomSalt());
        entity.setPassword(ShiroUtils.genPassword("123456",entity.getSalt()));
        entity.setIsLock(1);    //分布式的时候用的锁，防止多ip操作同一用户，现在暂时放着
        baseMapper.insert(entity);
        saveBatchMapUserRole(entity);   //在 user-role的关联表中添加数据，保证数据的一致性
        return ResponseResult.success("添加成功");
    }

    @Override
    public ResponseResult resetPwd(UserInfoVo entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setSalt(ShiroUtils.getRandomSalt());
            entity.setPassword(ShiroUtils.genPassword("123456", entity.getSalt()));
            return ResponseResult.success("重置成功");
        } else {
            return ResponseResult.success("重置失败，ID不能为空");
        }
    }

    @Override
    public ResponseResult updateEntity(UserInfoVo entity) {
        //查询user_info表中的所有name 是否和 添加的entity的name 有相同的
        List<UserInfo> existList = baseMapper.selectList(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getName,entity.getName()));
        //如果有则返回error
        if(!CollectionUtils.isEmpty(existList) && existList.size()>1){
            return ResponseResult.error("数据库中存在多个用户名："+entity.getName());
        }else if(existList.size() == 1 && !existList.get(0).getId().equals(entity.getId())){
            return ResponseResult.error("用户名已存在"+entity.getName());
        }else{
            baseMapper.updateById(entity);
            if(StringUtils.isNotEmpty(entity.getRoleIds())){
                //重置user_role关联表中的关系
                mapUserRoleMapper.delete(new LambdaQueryWrapper<MapUserRole>()
                        .eq(MapUserRole::getUserId,entity.getId()));
                saveBatchMapUserRole(entity);
            }
        }
        return ResponseResult.success("更新成功");
    }

    @Override
    public UserInfoVo getByName(String userName) {
        UserInfoVo userInfoVo = new UserInfoVo();
        UserInfo userInfo = baseMapper.selectOne(
                new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getName, userName));
        BeanUtils.copyProperties(userInfo, userInfoVo);
/*        userInfoVo.setRoleInfoList(roleInfoMapper.listByUserId(userInfoVo.getId()));
        userInfoVo.setResourceInfoList(resourceInfoMapper.listByUserId(userInfoVo.getId()));*/
        return userInfoVo;
    }

    @Override
    public ResponseResult updateInfoOrPwdById(UserInfoVo entity) {
        //获得session中的用户信息
        UserInfoVo userInfoVo = (UserInfoVo) ShiroUtils.getSessionAttribute(SessionConst.USER_INFO_SESSION);

        System.out.println("entity.id="+entity.getId());
        System.out.println("userInfoVo.id="+userInfoVo.getId());

        //设置将要修改的数据id为登录用户的id
        entity.setId(userInfoVo.getId());
        //1->修改基本信息  2->修改密码
        if ("1".equals(entity.getType())) {
            baseMapper.updateById(entity);
            //重新设置session中的登录用户信息，确保刷新的时候是最新数据
            ShiroUtils.setSessionAttribute(SessionConst.USER_INFO_SESSION, getByName(entity.getName()));
            return ResponseResult.success("更新成功");
        } else if ("2".equals(entity.getType())) {
            if (StringUtils.isNotEmpty(entity.getOldPassword()) && StringUtils.isNotEmpty(entity.getPassword())) {
                //重新加密
                String oldPassword = ShiroUtils.genPassword(entity.getOldPassword(), userInfoVo.getSalt());

                System.out.println("oldPassword="+oldPassword);

                if ((oldPassword.equals(userInfoVo.getPassword()))) {
                    //新密码重新加密
                    baseMapper.updateById(new UserInfo().setId(userInfoVo.getId())
                            .setPassword(ShiroUtils.genPassword(entity.getPassword(), userInfoVo.getSalt())));
                    return ResponseResult.success("修改密码成功，请重新登录");
                } else {
                    return ResponseResult.error("旧密码错误");
                }
            } else {
                return ResponseResult.error("更新密码不能为空");
            }
        } else {
            return ResponseResult.error("更新类型不能为空");
        }
    }

    private void saveBatchMapUserRole(UserInfoVo entity) {
        List<MapUserRole> list = new ArrayList<>();
        String[] split = entity.getRoleIds().split(",");

        for (int i = 0; i < split.length; i++) {
            System.out.println(i+"="+split[i]);
        }

        for (int i = 0; i < split.length; i++) {
            list.add(new MapUserRole().setUserId(entity.getId()).setRoleId(Integer.valueOf(split[i])));
        }
        //批量插入用户和角色之间的关联数据行
        mapUserRoleService.saveBatch(list, list.size());
    }
}
