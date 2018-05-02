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
@ApiModel(value = "仓库")
@Entity
public class Warehouse extends AbstractPersistable<Long> {

    @ApiModelProperty(value = "名称", example = "商场")
    private String name;

    @ApiModelProperty(value = "管理员")
    @OneToOne
    private Employee manager;

    @ApiModelProperty(value = "电话")
    private String phone;

}
