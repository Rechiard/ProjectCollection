package com.example.frame.dto.layuimini;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * @author bobo
 * @date 2021/04/19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class MenuInfo {

    private String title;

    private String icon;

    private String href;

    private String target;

    private List<MenuInfo> child;
}
