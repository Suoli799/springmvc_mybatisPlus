package com.atguigu.mp.config;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@MapperScan(basePackages = {"com.atguigu.mp.mybatis.mapper"})
@ComponentScan(basePackages = {"com.atguigu.mp"})
@Configuration
public class MPConfig {
    @Autowired
    private DBProperties dbProperties;
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(dbProperties.getUserName());
        comboPooledDataSource.setPassword(dbProperties.getPassword());
        comboPooledDataSource.setDriverClass(dbProperties.getDriverClassName());
        System.out.println(dbProperties.getDriverClassName());
        comboPooledDataSource.setJdbcUrl(dbProperties.getUrl());
        return comboPooledDataSource;
    }

    /**
     * 给SqlSessionFactory设置一个mybatisplus的全局配置项
     *  比如：可以设置主键策略，设置实体属性驼峰转列命下划线
     * @return
     */
    public GlobalConfiguration globalConfiguration(){
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        //设置主键策略为自增
        globalConfiguration.setIdType(IdType.AUTO.getKey());
        //默认实体属性转驼峰是true.所以不用设置
        //globalConfiguration.setDbColumnUnderline(true);
        return globalConfiguration;
    }
    @Bean
    @Primary
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean() throws PropertyVetoException {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(this.dataSource());
        //将全局配置设置到sqlSessionFactory中
        mybatisSqlSessionFactoryBean.setGlobalConfig(this.globalConfiguration());
        return mybatisSqlSessionFactoryBean;
    }
}
