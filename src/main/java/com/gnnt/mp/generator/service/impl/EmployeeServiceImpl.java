package com.gnnt.mp.generator.service.impl;

import com.gnnt.mp.generator.entity.Employee;
import com.gnnt.mp.generator.mapper.EmployeeMapper;
import com.gnnt.mp.generator.service.EmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suoli
 * @since 2019-10-16
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
