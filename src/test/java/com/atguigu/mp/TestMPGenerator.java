package com.atguigu.mp;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class TestMPGenerator {
    @Test
    public void testGenerator(){
        //1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(true)//是否支持AR模式
                    .setAuthor("suoli") //设置作者
                    .setOutputDir("D:\\git_repository\\springmvc_mybatisPlus\\src\\main\\java") //设置代码生成的路径
                    .setFileOverride(true) //文件覆盖；多次生成时设置
                    .setIdType(IdType.AUTO) //设置主键生成策略
                    .setServiceName("%sService") //设置service层的接口的名字首字母是否为I,默认为I; 不想要可以设置为这样
                    .setBaseResultMap(true) //设置resultMap
                    .setBaseColumnList(true); //设置生成基础的列
        //2、数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL) //设置数据库类型
                        .setDriverName("com.mysql.jdbc.Driver") //设置driverClassName
                        .setUsername("mp") //设置用户名
                        .setPassword("mp123456")    //设置密码
                        .setUrl("jdbc:mysql://127.0.0.1:3306/mp?useUnicode=true&characterEncoding=utf8"); //设置连接url

        //3、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true) //全局大写命名
                        .setDbColumnUnderline(true) //指定表名、字段是否使用下划线
                        .setNaming(NamingStrategy.underline_to_camel) //数据库表转实体的命名策略，下划线转驼峰
                        .setTablePrefix("tb_") //设置表明前缀
                        .setInclude("tb_employee"); //生成的表
        //4、包名策略
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.gnnt.mp.generator") //设置父包名
                        .setMapper("mapper")
                        .setService("service")
                        .setServiceImpl("service.impl")
                        .setController("controller")
                        .setEntity("entity")
                        .setXml("mapper.xml");
        //5.整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                    .setDataSource(dataSourceConfig)
                    .setStrategy(strategyConfig)
                    .setPackageInfo(packageConfig);
        //6、执行
        autoGenerator.execute();


    }
}
