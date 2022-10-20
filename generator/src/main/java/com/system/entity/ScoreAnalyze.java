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
@ApiModel(value="ScoreAnalyze对象", description="")
public class ScoreAnalyze implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("s_stuId")
    private String sStuid;

    @TableField("s_stuName")
    private String sStuname;

    @TableField("s_stuSex")
    private String sStusex;

    @TableField("s_stuCollege")
    private String sStucollege;

    @TableField("s_stuGrade")
    private String sStugrade;

    @TableField("s_stuProfession")
    private String sStuprofession;

    @TableField("s_stuClass")
    private String sStuclass;

    private Integer sBydsSpbgh;

    private Integer sBydsDssl;

    private Double sBysjZyzfw;

    private Integer sBysjShsj;

    private Integer sByjt;

    private Integer sByxs;


}
