package com.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author shuxiangxiaoyuan
 * @since 2022-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Information对象", description="")
public class Information implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "InformationID", type = IdType.AUTO)
    private Integer InformationID;

    @TableField("InformationContent")
    private String InformationContent;

    @TableField("InformationImage")
    private String InformationImage;

    @TableField("InformationCreateTime")
    private String InformationCreateTime;

    private String title;

    private String introduction;

    private Integer banner;

    private String type;

    private Integer statu;


}
