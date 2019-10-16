package com.atguigu.mp.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 使用Active Rcoader模式
 */
@Data
@TableName("tb_employee")
public class EmployeeAR extends Model {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    @TableField(value="salart",exist = false)
    private Double salary;

    @Override
    public String toString() {
        return "EmployeeAR{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

    protected Serializable pkVal() {
        return id;
    }
}
