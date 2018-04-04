package com.wixdom.bookcenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "部门")
public class Department {

    @ApiModelProperty(value = "名称", example = "工具书部")
    private String name;

    @ApiModelProperty(value = "经理")
    private Employee manager;

    @ApiModelProperty(value = "地址", example = "广州市天河路123号")
    private String address;

}
