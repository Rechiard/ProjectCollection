package com.shida.labchecksys.pojo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "lab_function")
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "function_id")
    private long functionId;

    @Column(name = "function_name")
    private String functionName;

    //@JsonBackReference主要可以使标注属性避免被json序列化,进而避免多对多关系的查询中出现死循环的情况。
    @JsonBackReference
    @ManyToMany(mappedBy = "authorities")
    private List<Role> roles;
}
