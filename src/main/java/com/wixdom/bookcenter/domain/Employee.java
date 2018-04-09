package com.wixdom.bookcenter.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.minlia.module.data.entity.AbstractEntity;
import com.wixdom.bookcenter.enumeration.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Henry Lin badcop@163.com
 */
@Data
@ApiModel(value = "员工")
public class Employee extends Model<Employee> {

    @ApiModelProperty(value = "工号", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户id", example = "1234567890")
    private Long guid;

    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @ApiModelProperty(value = "年龄", example = "25")
    private Integer age;

    @ApiModelProperty(value = "性别", example = "MALE")
    private GenderEnum gender;

    @ApiModelProperty(value = "职位", example = "经理")
    private String position;

    @ApiModelProperty(value = "月工资", example = "8888.88")
    private BigDecimal salary;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
