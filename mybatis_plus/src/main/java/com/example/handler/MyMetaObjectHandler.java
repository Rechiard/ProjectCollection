package com.example.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component  //一定不要忘记把处理器加入到springboot中
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill....");
        //default MetaObjectHandler setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);

    }

    //更新的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill....");

        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
