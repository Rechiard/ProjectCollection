package com.example;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybaitsPlusApplicationTests {

    //继承了BaseMapper，所有的方法都来自己父类
    //也可以编写自己的扩展方法
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {

        //查询全部用户
        //参数是一个Wrapper，条件构造器，这里我们先不用 ，给一个null
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //测试插入
    @Test
    public void  testInsert(){
        User user = new User();
        user.setName("bobo");
        user.setAge(21);
        user.setEmail("616316004@qq.com");
        int res = userMapper.insert(user);  //自动生成id
        System.out.println(res);
        System.out.println(user);
    }

    //测试更新
    @Test
    public void  testUpdate(){
        User user = new User();
        user.setId(1383360557825556481L);
        user.setAge(100);
        user.setName("rourou");
        int res = userMapper.updateById(user);  //自动生成id
        System.out.println(res);
    }

    //测试乐观锁成功
    @Test
    public void OptimisticLocker(){
        //查询用户信息
        User user = userMapper.selectById(1L);
        //修改
        user.setName("xiaofeng");
        user.setEmail("1770832083@qq.com");
        //执行更新操作
        userMapper.updateById(user);

    }


    //测试乐观锁失败
    @Test
    public void OptimisticLocker2(){
        //线程1
        User user = userMapper.selectById(1L);
        user.setName("xiaofeng");
        user.setEmail("1770832083@qq.com");

        //模拟另外一个线程执行插队操作
        User user2 = userMapper.selectById(1L);
        user.setName("xiaofeng");
        user.setEmail("1770832083@qq.com");
        userMapper.updateById(user2);

        //自旋锁来多次提交，以免触发乐观锁
        userMapper.updateById(user);    //如果没有乐观锁就会覆盖插队线程的值
    }

    //测试查询
    @Test
    public void testSelectById(){
        //单个查询
        System.out.println(userMapper.selectById(1L));
        //批量查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        users.forEach(System.out::println);

        //条件查询 map
        HashMap<String,Object> map = new HashMap<>();
        //自定义查询
        map.put("name","bobo");
        map.put("age",3);
        List<User> users1 = userMapper.selectByMap(map);
        users1.forEach(System.out::println);

    }

    //测试分页查询
    @Test
    public void testPage(){
        //查询第一页，一页显示五条记录
        // 参数一：当前页
        // 参数二：页面大小
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }

    //测试删除
    @Test
    public void testDeleteById(){
        //单体删除
        int i = userMapper.deleteById(1383360557825556484L);
        System.out.println(i);

        //批量删除
        int i1 = userMapper.deleteBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(i1);

        HashMap<String,Object> map = new HashMap<>();
        map.put("name","Billie");
        map.put("age",24);

        int i2 = userMapper.deleteByMap(map);
        System.out.println(i2);
    }

    @Test
    public void testLuoJoDeleteById(){
        int i = userMapper.deleteById(1383360557825556484L);
        System.out.println(i);
    }

}
