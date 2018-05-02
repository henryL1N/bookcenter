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
@ApiModel(value = "购书中心")
@Entity
public class BookCenter extends AbstractPersistable<Long> {

    @ApiModelProperty(value = "名称", example = "广州购书中心")
    private String name;

    @ApiModelProperty(value = "总经理")
    @OneToOne
    private Employee generalManager;

    @ApiModelProperty(value = "地址", example = "广州市天河路123号")
    private String address;

}
