package com.example;

import com.example.system.service.DeptInfoService;
import com.example.system.service.RoleInfoService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Admin2ApplicationTests {

    @Test
    void contextLoads() {
 /*       Md5Hash md5Hash = new Md5Hash("123456", "VKqvum8YW0xNaTDFbrcijhTG6wUP5xAZ", 2);
        System.out.println(md5Hash);*/
        System.out.println(md5("123456","VKqvum8YW0xNaTDFbrcijhTG6wUP5xAZ"));
    }

    public static final String md5(String password,String salt){
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        //密码
        Object source = password;
        //加密次数
        int hashIterations = 2;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
        return result.toString();
    }

    @Autowired
    RoleInfoService roleInfoService;
    @Autowired
    DeptInfoService deptInfoService;

    @Test
    void fun(){
        //roleInfoService.list();
        //deptInfoService.list();
        //deptInfoService.listByDTree();
        //roleInfoService.listByTable();
    }

}
