package com.shida.labchecksys.pojo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "lab_role")
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    //作为被维护端，只需要设置mappedBy属性，其值与User中对应List类型变量名相同
    //@JsonBackReference可以避免属性被json序列化，出现死循环
    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private List<User> users;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_auth", //name是表名
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id")
    )
    private List<Authority> authorities;
}
