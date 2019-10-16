package com.atguigu.mp;

import com.atguigu.mp.config.MPConfig;
import com.atguigu.mp.pojo.Employee;
import com.atguigu.mp.pojo.EmployeeAR;
import com.baomidou.mybatisplus.activerecord.Model;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

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

    @Test
    public void test_ARSelect(){
        EmployeeAR employee = new EmployeeAR();
//        employee.setId(118);
//        employee = employee.selectById();
//        System.out.println("根据ID查询结果:"+employee.toString());

        List<EmployeeAR> employeeARS = employee.selectAll();
        System.out.println("数据库中所有数据："+employeeARS.toString());

    }
    @Test
    public void test_ARDelete(){
        EmployeeAR employee = new EmployeeAR();
        employee.setId(118);
        //当数据库中没有数据时，同样返回结果为true
        boolean result = employee.deleteById();
        System.out.println("删除结果："+result);

    }
}
