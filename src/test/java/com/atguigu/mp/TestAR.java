package com.atguigu.mp;

import com.atguigu.mp.config.MPConfig;
import com.atguigu.mp.pojo.EmployeeAR;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAR {
    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MPConfig.class);

    @Test
    public void test_ARInsert(){
        EmployeeAR employee = new EmployeeAR();
        employee.setName("pangdahai");
        employee.setAge(25);
        employee.setEmail("pdh@atguigu.com");
        boolean result = employee.insert();
        System.out.println("插入操作结果："+result);
    }
}
