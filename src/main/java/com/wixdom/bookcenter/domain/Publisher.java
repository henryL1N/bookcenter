package com.wixdom.bookcenter.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "出版商")
public class Publisher extends Model<Publisher> {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "名称", example = "商务印书馆")
    private String name;

    @ApiModelProperty(value = "电话", example = "010-65256803")
    private String phone;

    @ApiModelProperty(value = "地址", example = "北京王府井大街36号")
    private String address;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
