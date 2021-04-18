package com.example.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //对应数据库中的主键(uuid、自增id，雪花算法，redis、zookeeper）
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;

    @Version    //乐观锁Version注解
    private Integer version;

    @TableLogic //1正常，2删除
    private Integer status;

    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
