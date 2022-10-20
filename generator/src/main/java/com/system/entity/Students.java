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
@ApiModel(value="Students对象", description="")
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "s_id", type = IdType.AUTO)
    private Integer sId;

    @TableField("s_studentId")
    private String sStudentid;

    private String sName;

    private Integer sSex;

    private String sSchool;

    private String sCollege;

    private String sGrade;

    private String sProfession;

    private String sClass;

    private String sClasstype;

    private String sJobstatus;

    private String sTutor;

    private String sPhone;

    private String sIdcard;

    private String sPassword;

    @TableField("s_createTime")
    private Date sCreatetime;

    @TableField("s_changeTime")
    private Date sChangetime;

    private String sPicture;

    private String sToken;

    private Date sTokenendtime;

    @TableField("s_deviceId")
    private String sDeviceid;


}
