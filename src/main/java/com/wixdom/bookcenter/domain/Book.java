package com.wixdom.bookcenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "书")
public class Book {

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

}
