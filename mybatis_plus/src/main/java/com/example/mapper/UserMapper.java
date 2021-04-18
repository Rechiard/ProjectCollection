package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//在对应的Mapper上面实现基本的接口BaseMapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    //所有的CRUD编写全部完成

}
