package com.atguigu.mp.mybatis.mapper;

import com.atguigu.mp.pojo.Employee;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

/**
 * Mapper接口：
 *  mybatis:
 *      在mapper接口中编写curd相关方法，提供mapper接口对应的sql映射文件及 方法对应的sql语句
 *  mp:
 *      让我们自己编写的xxxxMapper接口继承BaseMapper<T>接口即可；
 *      BaseMapper<T>:泛型指当前mapper接口所操作的类
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
