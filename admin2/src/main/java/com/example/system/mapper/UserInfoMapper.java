package com.example.system.mapper;

import com.example.system.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.system.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    int countByTable(@Param("entity") UserInfoVo entity);

    List<UserInfoVo> listByTable(@Param("entity") UserInfoVo entity);

}
