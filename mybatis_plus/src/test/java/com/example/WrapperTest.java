package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        //查询name不为空的用户，并且邮箱不为空的用户，并且年龄大于等于25的
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        Wrapper.isNotNull("name").
                isNotNull("email").
                ge("age",25);   //大于等于
        userMapper.selectList(Wrapper);
    }

    @Test
    void test2(){
        //查询名字为 bobo
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        Wrapper.eq("name","bobo");
        User user = userMapper.selectOne(Wrapper);
        System.out.println(user);
    }

    @Test
    void test3(){
        //查询年龄在 20-30 之间的用户
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        Wrapper.between("age",20,30);   //区间
        Integer integer = userMapper.selectCount(Wrapper);  //查询结果数
        System.out.println(integer);
    }

    //模糊查询
    @Test
    void test4(){
        //查询年龄在 20-30 之间的用户
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        //左和右 %e%
        Wrapper.notLike("name","e")
                .likeRight("email","6");

        List<Map<String, Object>> maps = userMapper.selectMaps(Wrapper);//查询结果数
        maps.forEach(System.out::println);
    }

    @Test
    void test5(){
        //查询年龄在 20-30 之间的用户
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        //id 在子查询中查出来
        Wrapper.inSql("id","select id from user where id<3");
        List<Object> objects = userMapper.selectObjs(Wrapper);//查询结果数
        objects.forEach(System.out::println);
    }

    @Test
    void test6(){
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        //通过id进行排序
        Wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(Wrapper);//查询结果数
        users.forEach(System.out::println);
    }

}
