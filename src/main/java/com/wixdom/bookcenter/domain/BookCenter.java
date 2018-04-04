package com.wixdom.bookcenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "购书中心")
public class BookCenter {

    @ApiModelProperty(value = "名称", example = "广州购书中心")
    private String name;

    @ApiModelProperty(value = "总经理")
    private Employee generalManager;

    @ApiModelProperty(value = "地址", example = "广州市天河路123号")
    private String address;

}
