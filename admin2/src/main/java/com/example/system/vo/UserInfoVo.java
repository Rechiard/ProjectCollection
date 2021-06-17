package com.example.system.vo;

import com.example.system.entity.ResourceInfo;
import com.example.system.entity.RoleInfo;
import com.example.system.entity.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author bobo
 * @date 2021/04/20
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserInfoVo extends UserInfo {

    private String type;

    private String roleIds;

    private String roleName;

    private String oldPassword;

    private String deptName;

    private List<RoleInfo> roleInfoList;

    private List<ResourceInfo> resroleourceInfoList;

}
