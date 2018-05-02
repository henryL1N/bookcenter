package com.wixdom.bookcenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

/**
 * @author Henry Lin badcop@163.com
 */
@Data
@ApiModel(value = "出版商")
@Entity
public class Publisher extends AbstractPersistable<Long> {

    @ApiModelProperty(value = "名称", example = "商务印书馆")
    private String name;

    @ApiModelProperty(value = "电话", example = "010-65256803")
    private String phone;

    @ApiModelProperty(value = "地址", example = "北京王府井大街36号")
    private String address;

}
