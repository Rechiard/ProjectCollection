package com.example.system.vo;

import com.example.system.entity.RoleInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author bobo
 * @date 2021/04/20
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class RoleInfoVo extends RoleInfo {

    private String resourceIds;
}

