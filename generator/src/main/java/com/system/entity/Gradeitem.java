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
@ApiModel(value="Gradeitem对象", description="")
public class Gradeitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模块成绩详情")
    @TableId(value = "gitem_id", type = IdType.AUTO)
    private Integer gitemId;

    @TableField("gitem_stuId")
    private String gitemStuid;

    @TableField("gitem_stuName")
    private String gitemStuname;

    @TableField("gitem_stuSex")
    private String gitemStusex;

    @TableField("gitem_stuCollege")
    private String gitemStucollege;

    @TableField("gitem_stuGrade")
    private String gitemStugrade;

    @TableField("gitem_stuClass")
    private String gitemStuclass;

    @TableField("gitem_stuProfession")
    private String gitemStuprofession;

    private String gitemAitem;

    @TableField("gitem_aName")
    private String gitemAname;

    @TableField("gitem_aId")
    private String gitemAid;

    private String gitemTeacher;

    private String gitemAddress;

    private Date gitemDate;

    private String gitemGrade;

    private Double gitemCredit;

    private Integer gitemType;


}
