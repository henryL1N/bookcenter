package com.wixdom.bookcenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "仓库")
public class Warehouse {

    @ApiModelProperty(value = "名称", example = "商场")
    private String name;

    @ApiModelProperty(value = "管理员")
    private Employee manager;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "库存项")
    private Set<StockItem> stockItems;

}
