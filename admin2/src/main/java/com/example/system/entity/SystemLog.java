package com.example.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.example.frame.dto.BaseEntity;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_log")
@ApiModel(value="SystemLog对象", description="系统日志表")
public class SystemLog extends BaseEntity {


    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "操作用户")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "操作描述")
    @TableField("operation")
    private String operation;

    @ApiModelProperty(value = "操作类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "耗时(毫秒)")
    @TableField("time")
    private Integer time;

    @ApiModelProperty(value = "操作方法")
    @TableField("method")
    private String method;

    @ApiModelProperty(value = "操作参数")
    @TableField("params")
    private String params;

    @ApiModelProperty(value = "IP地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "操作地点")
    @TableField("location")
    private String location;

    @ApiModelProperty(value = "操作时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "状态 1-正常 2-删除")
    @TableField(value = "status", fill = FieldFill.INSERT)
    private Integer status;


}
