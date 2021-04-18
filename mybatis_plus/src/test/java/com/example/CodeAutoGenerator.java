package com.example;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class CodeAutoGenerator {

    public static void main(String[] args) {
        //需要构建一个代码自动生成器对象
        AutoGenerator mpg = new AutoGenerator();
        //配置策略

        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("bobo");
        gc.setOpen(false);
        gc.setFileOverride(false);  //是否覆盖
        gc.setServiceImplName("%sService"); //去掉Service的I前缀
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        //2.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);

        mpg.setDataSource(dsc);

        //3.包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("blog");
        pc.setParent("com.example");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");

        mpg.setPackageInfo(pc);

        //4.策略配置    数据库和entity层交互
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user");    //设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);  //名称命名规则，下划线->驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);    //列名命名规则，下划线->驼峰命名
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");  //设置entity层的数据类继承的BaseEntity父类
        strategy.setEntityLombokModel(true);    //设置是否需要lombok
        strategy.setLogicDeleteFieldName("status"); //设置逻辑删除字段

        //自动填充配置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        strategy.setVersionFieldName("version");    //乐观锁
        strategy.setRestControllerStyle(true);  //设置controller的restful的驼峰命名风格
        strategy.setControllerMappingHyphenStyle(true); //localhost://8080/hello_id_2

        mpg.setStrategy(strategy);

        mpg.execute();  //执行
    }
}
