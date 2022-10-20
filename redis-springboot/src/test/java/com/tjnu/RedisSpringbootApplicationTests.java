package com.tjnu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjnu.config.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisSpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        //redisTemplate 操作不同的数据类型用opsForxxx，api和直接linux操作一样
        //opsForValue   操作字符串 类似String
        //opsForList    操作list  类似list
        //...   五大数据类型

        //除了基本的操作，常用的方法可以直接通过redisTemplate操作，比如事务、CRUD
/*        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        System.out.println(connection.ping());
        connection.flushDb();
        connection.flushAll();*/

        redisTemplate.opsForValue().set("mykey","bobo");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    public void test() throws JsonProcessingException {
        //真是的开发一般都使用json来传递对象
        User user = new User("龚剑波", 3);
        //用于将user对象序列化,如果对象未被序列化，则会直接报错
        //所以在企业中，所有的pojo，都需要进行序列化操作
        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

}
