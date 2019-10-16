package com.atguigu.mp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource({"classpath:/db.properties"})
public class DBProperties {
    @Value("${db.userName}")
    private String userName;
    @Value("${db.password}")
    private String password;
    @Value("${db.dirverClassName}")
    private String driverClassName;
    @Value("${db.Url}")
    private String url;
}
