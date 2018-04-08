package com.wixdom.bookcenter.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "部门")
public class Department extends Model<Department> {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "名称", example = "工具书部")
    private String name;

    @ApiModelProperty(value = "经理")
    private Employee manager;

    @ApiModelProperty(value = "地址", example = "广州市天河路123号")
    private String address;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
