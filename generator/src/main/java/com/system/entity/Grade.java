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
@ApiModel(value="Grade对象", description="")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模块成绩汇总")
    @TableId(value = "g_id", type = IdType.AUTO)
    private Integer gId;

    @TableField("g_stuId")
    private String gStuid;

    @TableField("g_stuName")
    private String gStuname;

    @TableField("g_stuSex")
    private String gStusex;

    @TableField("g_stuCollege")
    private String gStucollege;

    @TableField("g_stuGrade")
    private String gStugrade;

    @TableField("g_stuClass")
    private String gStuclass;

    @TableField("g_stuProfession")
    private String gStuprofession;

    private Double gBydsSpbgh;

    private Double gBydsDssl;

    private Double gByxqLljx;

    private Double gByxqStlx;

    private Double gByxqSjdl;

    private Double gBysjShsj;

    private Double gBysjZyfu;

    private Double gByjtZjrwdjt;

    private Double gByjtZjdjt;

    private Double gByjtSyjt;

    private Double gByjtYzxwc;

    private Double gByjtZtsl;

    private Double gByxsXqst;

    private Double gByxsYgcp;

    private Double gByxsWmqsjs;

    private Double gByxsTywhhd;

    private Double gBysyCgsz;

    private Double gBysyJhxx;

    private Double gBysyYxfx;

    private Double gByjtXqnyt;

    private Double gByjtMhjt;

    private Double gByjtQcyy;


}
