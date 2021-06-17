package com.example.system.vo;

import com.example.system.entity.ResourceInfo;
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
public class ResourceInfoVo extends ResourceInfo {

    private String pidName;
}
