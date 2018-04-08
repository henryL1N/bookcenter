package com.wixdom.bookcenter.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.minlia.module.data.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "员工")
public class Employee extends Model<Employee> {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户id", example = "1234567890")
    private Long guid;

    @ApiModelProperty(value = "工号", example = "1")
    private Long employeeId;

    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @ApiModelProperty(value = "年龄", example = "25")
    private Integer age;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
