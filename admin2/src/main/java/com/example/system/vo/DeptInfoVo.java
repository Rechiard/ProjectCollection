package com.example.system.vo;

import com.example.system.entity.DeptInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DeptInfoVo extends DeptInfo {

    private String pidName;
}
