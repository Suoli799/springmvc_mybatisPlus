package com.atguigu.mp;

import com.atguigu.mp.config.MPConfig;
import com.atguigu.mp.mybatis.mapper.EmployeeMapper;
import com.atguigu.mp.pojo.Employee;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 测试MP的条件构造器
 */
public class IOCTestEntityWrapper {
    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MPConfig.class);
    private EmployeeMapper employeeMapper = applicationContext.getBean("employeeMapper",EmployeeMapper.class);

    
    @Test
    public void test_SelectUserWrapper(){
        //查询(name="Billie" and age=24) or (name="Jack" and age=20)
//        List<Employee> employees = this.employeeMapper.selectList(new EntityWrapper<Employee>()
//                .eq("name", "Billie")
//                .and()
//                .eq("age", 24)
//                .or()
//                .eq("name", "Jack")
//                .and()
//                .eq("age", 20)
//        );
//        System.out.println("查询结果：\n"+employees.toString());

        //根据age进行排序，升序/降序，实现简单分页
        List<Employee> employeeList = this.employeeMapper.selectList(new EntityWrapper<Employee>()
                //.orderBy("age") //默认是升序
                //.last("asc") //手工拼接sql,可以和.orderBy（）一起使用，但是有sql注入的风险
                .orderDesc(Lists.newArrayList("age")) //倒序排
                .last("limit 1,3")//手工拼接sql实现简单分页
        );
        System.out.println("查询结果为：\n"+employeeList.toString());


        //使用Condition方式构造查询
        List<Employee> list = this.employeeMapper.selectList(Condition.create()
                .orderDesc(Lists.newArrayList("age"))
                .last(" limit 1,3")
        );
        System.out.println("查询结果为：\n"+employeeList.toString());


    }

    @Test
    public void test_updateUseWrapper() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //通过Contructor构造器创建对象实例
        Employee employee = Employee.class.getConstructor().newInstance();
        employee.setAge(50);
        //更新条件(name="Billie" or age=24) 的数据的age为50
        Integer result = this.employeeMapper.update(employee, new EntityWrapper<Employee>()
                .eq("name", "Billie")
                .or()
                .eq("name", "Jack")
        );
        System.out.println("更新影响条数为："+result);
    }

    @Test
    public void test_deleteUseWrapper() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class aClass = Class.forName("com.atguigu.mp.pojo.Employee");
        //通过Class的newInstance()创建对象实例
        Employee employee =(Employee) aClass.newInstance();
        employee.setAge(20);
        employee.setName("fahai");
        Integer result = this.employeeMapper.delete(new EntityWrapper<Employee>()
                .eq("age", employee.getAge())
                .and()
                .eq("name", employee.getName())
        );
        System.out.println("删除影响条数为："+result);

    }

}
