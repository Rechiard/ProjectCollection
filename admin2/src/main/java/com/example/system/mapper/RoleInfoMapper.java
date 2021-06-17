package com.example.system.mapper;

import com.example.system.entity.RoleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.system.vo.RoleInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {

    int countByTable(@Param("entity") RoleInfoVo entity);

    List<RoleInfoVo> listByTable(@Param("entity") RoleInfoVo entity);

    List<RoleInfo> listByUserId(@Param("userId") Integer userId);
}
