package com.atguigu.mp;

import com.atguigu.mp.config.MPConfig;
import com.atguigu.mp.mybatis.mapper.EmployeeMapper;
import com.atguigu.mp.pojo.Employee;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IOCTest {
    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MPConfig.class);
    private EmployeeMapper employeeMapper = applicationContext.getBean("employeeMapper",EmployeeMapper.class);

    public EmployeeMapper getEmployeeMapper() {
        return (EmployeeMapper)applicationContext.getBean("employeeMapper");
    }
    @Test
    public void printIOC_Bean(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
        System.out.println(dataSource);
//        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = applicationContext.getBean("sqlSessionFactoryBean", MybatisSqlSessionFactoryBean.class);
//        System.out.println(sqlSessionFactoryBean);
    }

    /**
     * 测试mp的查询
     * @throws SQLException
     */
    @Test
    public void testMP_Select(){
        Employee employee = employeeMapper.selectById(101);
        System.out.println(employee.toString());
        // System.out.println(dataSource.getConnection());
    }

    @Test
    public void testMP_SelectOne(){
        //通过多列进行查询 id、name
        Employee employee = new Employee();
        employee.setId(116);
        employee.setName("xiaowa");
        //注意：此方法返回结果为一条数据，当根据条件查询出多条时会报错
        Employee result = getEmployeeMapper().selectOne(employee);
        System.out.println("查询结果："+result.toString());
    }

    @Test
    public void testMP_SelectBatchIds(){
        ArrayList<Integer> ids = Lists.newArrayList(101, 102, 103, 104);
        List<Employee> employees = getEmployeeMapper().selectBatchIds(ids);
        System.out.println(employees.toString());
    }

    @Test
    public void testMP_SelectByMap(){
        HashMap<String, Object> paramterMap = Maps.newHashMap();
        paramterMap.put("name","fahai");
        List<Employee> employees = getEmployeeMapper().selectByMap(paramterMap);
        System.out.println("查询结果："+employees.toString());

    }

    @Test
    public void testMP_SelectPage(){
        //从打印的sql看没有limit 因为page使用的是内存分页，并不是物理分页
        List<Employee> employees = getEmployeeMapper().selectPage(new Page(1, 3), null);
        System.out.println("查询结果："+employees.toString());
    }
    
    @Test
    public void testMP_Insert(){
        Employee employee = new Employee();
       // employee.setId(2);
        employee.setName("xiaowa");
        employee.setAge(20);
        employee.setEmail("xiaowa@atguigu.com");

        // insert 方法在插入时，会根据实体的每一个属性的进行非空判断，只有非空的属性对应的字段才会出现在SQL语句中。
        //int result = getEmployeeMapper().insert(employee);

        // insertAllColumn方法在插入时，不管属性是否非空，属性所对应的字段都会出现在SQL语句中。
        int result = getEmployeeMapper().insertAllColumn(employee);

        System.out.println("插入结果为："+result);
        //获取当前数据插入到数据库以后的Id值，mybatisPlus默认插入成功后，会进行赋值
        System.out.println("主键值为："+employee.getId());
    }
    @Test
    public void testMP_Update(){
        Employee employee = new Employee();
        //将这条数据的age和email更新
        employee.setId(2);
        employee.setAge(26);
        employee.setEmail("shashaHai@atguigu.com");

        //同insert方法一样，会进行属性的非空判断，只有非空 才会出现在sql中
        Integer result = this.getEmployeeMapper().updateById(employee);

        System.out.println("更新结果为："+result);
        //不管属性是否非空，属性所对应的字段都会出现在SQL语句中。 当有些属性没有赋值时，会导致数据库相应字段为空；
        this.getEmployeeMapper().updateAllColumnById(employee);
    }
}
