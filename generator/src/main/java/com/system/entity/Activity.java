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
@ApiModel(value="Activity对象", description="")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "r_id", type = IdType.AUTO)
    private Integer rId;

    private String rName;

    @TableField("r_T_id")
    private String rTId;

    private String rPicture;

    private String rIntroduction;

    private Integer rType;

    private Integer rStatus;

    private String rCollege;

    private Integer rAddress;

    @TableField("r_startTime")
    private Date rStarttime;

    @TableField("r_endTime")
    private Date rEndtime;

    @TableField("r_startTimeTwo")
    private Date rStarttimetwo;

    @TableField("r_endTimeTwo")
    private Date rEndtimetwo;

    @TableField("r_createTime")
    private Date rCreatetime;

    @TableField("r_changeTime")
    private Date rChangetime;

    private Integer rPopular;

    private String rZixun;

    private Integer rSearchnum;

    private Integer rClicknum;

    private Integer rMaximum;

    private Integer rNowmum;

    private Double rCredit;

    private Integer rItem;

    private Integer rRecsign;

    @ApiModelProperty(value = "轮播图置换状态")
    private Integer rTu;

    private Integer rZdcollege;

    private String rQrcode;


}
