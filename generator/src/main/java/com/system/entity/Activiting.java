package com.system.entity;

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
@ApiModel(value="Activiting对象", description="")
public class Activiting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("A_R_id")
    private Integer aRId;

    @TableField("A_R_Session")
    private Integer aRSession;

    @TableField("A_S_id")
    private String aSId;

    @TableField("A_status")
    private Integer aStatus;

    @TableField("A_grade")
    private Integer aGrade;

    @TableField("A_devicecode")
    private String aDevicecode;

    @TableField("A_level")
    private Integer aLevel;

    @TableField("A_firsttime")
    private Date aFirsttime;

    @TableField("A_secdevicecode")
    private String aSecdevicecode;

    @TableField("A_secondtime")
    private Date aSecondtime;

    @TableField("A_thirddevicecode")
    private String aThirddevicecode;

    @TableField("A_thirdtime")
    private Date aThirdtime;

    @TableField("A_fourdevicecode")
    private String aFourdevicecode;

    @TableField("A_fourtime")
    private Date aFourtime;


}
