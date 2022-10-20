package com.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@ApiModel(value="Jpush对象", description="")
public class Jpush implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "J_id", type = IdType.AUTO)
    private Integer jId;

    @TableField("J_st")
    private Integer jSt;

    @TableField("J_message")
    private String jMessage;

    @TableField("createTime")
    private Date createTime;

    private Integer jAcid;


}
