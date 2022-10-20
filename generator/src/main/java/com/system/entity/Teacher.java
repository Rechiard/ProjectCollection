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
@ApiModel(value="Teacher对象", description="")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "t_id", type = IdType.AUTO)
    private Integer tId;

    @TableField("t_teacherId")
    private String tTeacherid;

    private String tPassword;

    private String tName;

    private String tPhone;

    private Integer tDutyphone;

    private String tEmail;

    private String tCollege;

    private String tAddress;

    private String tOpinions;

    private String tPicture;

    private String tToken;

    private String tExpert;

    private String tResearchdirection;

    @TableField("t_changeTime")
    private Date tChangetime;

    @TableField("t_createTime")
    private Date tCreatetime;

    private Integer tFrequency;

    private Integer tType;

    private Integer tSearchnum;

    private String tIntroduction;

    private Integer tPower;


}
