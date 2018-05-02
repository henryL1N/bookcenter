package com.wixdom.bookcenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Henry Lin badcop@163.com
 */
@Data
@ApiModel(value = "库存项")
@Entity
public class StockItem extends AbstractPersistable<Long> {

    @ApiModelProperty(value = "书")
    @OneToOne
    private Book book;

    @ApiModelProperty(value = "数量", example = "1")
    private Integer quantity;

    @ApiModelProperty(value = "仓库")
    @OneToOne
    private Warehouse warehouse;

}
