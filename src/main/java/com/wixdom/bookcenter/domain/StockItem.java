package com.wixdom.bookcenter.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "库存项")
public class StockItem extends Model<StockItem> {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "书")
    private Book book;

    @ApiModelProperty(value = "数量", example = "1")
    private Integer quantity;

    @ApiModelProperty(value = "仓库")
    private Warehouse warehouse;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
