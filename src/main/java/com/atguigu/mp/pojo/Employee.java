package com.atguigu.mp.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @TableName:
 *      mybatisPlus会默认根据实体类的类名去数据库中查找响应的表,因此我们可以用@TableName注解来指定；
 * @TableId:
 *      表主键标示.
 *          value：实体属性与数据库列名相同时，可以不指定;当实体类属性与数据库字段不同时，用来指定数据哪个字段与实体当前字段来进行映射绑定.
 *          type:  表主键的类型；当添加数据时，如果我们不给定，则默认会以ID_WORKER	的方式自动创建，因此会报错；
 * @TableField:
 *          value: 同表主键主键的value作用相同。
 *          exist: 默认为true;当数据库表中没有列名与其属性对应时可以设置为false.
 *
 */
@Data
@TableName("tb_employee")
public class Employee {
   // @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    @TableField(value="salart",exist = false)
    private Double salary;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
