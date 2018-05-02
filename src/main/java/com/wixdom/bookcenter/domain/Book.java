package com.wixdom.bookcenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

/**
 * @author Henry Lin badcop@163.com
 */
@Data
@ApiModel(value = "书")
@Entity
public class Book extends AbstractPersistable<Long> {

    @ApiModelProperty(value = "书名", example = "新华字典")
    private String name;

    @ApiModelProperty(value = "规格", example = "64开")
    private String specification;

    @ApiModelProperty(value = "出版商")
    @OneToOne
    private Publisher publisher;

    @ApiModelProperty(value = "分类")
    @OneToOne
    private Category category;

    @ApiModelProperty(value = "成本价")
    private BigDecimal cost;

    @ApiModelProperty(value = "零售价")
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "批发价")
    private BigDecimal wholesalePrice;

}
