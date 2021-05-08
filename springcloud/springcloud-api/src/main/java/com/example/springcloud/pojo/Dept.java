package com.example.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
/*
    链式写法：
    Dept dept = new Dept();
    dept.setDeptNo(11).setDname('sss).setDb_srouce('001');
 */
@Accessors(chain = true)    //链式写法
public class Dept implements Serializable {

    private Long deptno;

    private String dname;

    //这个数据存在哪个数据库的字段~ 微服务，一个服务对应一个数据量，同一个信息可能存在不同的数据库
    private String db_source;

    public Dept(String dname){
        this.dname = dname;
    }
}
