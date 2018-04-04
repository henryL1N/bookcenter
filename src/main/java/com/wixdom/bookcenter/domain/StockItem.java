package com.wixdom.bookcenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "库存项")
public class StockItem {

    @ApiModelProperty(value = "书")
    private Book book;

    @ApiModelProperty(value = "数量", example = "1")
    private Integer quantity;

    @ApiModelProperty(value = "仓库")
    private Warehouse warehouse;

}
