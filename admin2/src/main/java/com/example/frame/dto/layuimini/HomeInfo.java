package com.example.frame.dto.layuimini;

import com.example.frame.dto.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author bobo
 * @date 2021/04/16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class HomeInfo extends BaseEntity {

    private String title;

    private String href;

}
