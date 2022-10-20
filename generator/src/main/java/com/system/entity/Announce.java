package com.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Announce对象", description="")
public class Announce implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "an_id", type = IdType.AUTO)
    private Integer anId;

    private String anTitle;

    private Date anTime;

    private String anContent;

    private Integer anStatus;

    private Integer anTu;


}
