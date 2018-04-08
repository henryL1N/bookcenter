package com.wixdom.bookcenter.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "书")
public class Book extends Model<Book> {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "书名", example = "新华字典")
    private String name;

    @ApiModelProperty(value = "规格", example = "64开")
    private String specification;

    @ApiModelProperty(value = "出版商")
    private Publisher publisher;

    @ApiModelProperty(value = "分类")
    private Category category;

    @ApiModelProperty(value = "成本价")
    private BigDecimal cost;

    @ApiModelProperty(value = "零售价")
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "批发价")
    private BigDecimal wholesalePrice;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
