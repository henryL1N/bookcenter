package com.wixdom.bookcenter.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "购书中心")
public class BookCenter extends Model<BookCenter> {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "名称", example = "广州购书中心")
    private String name;

    @ApiModelProperty(value = "总经理")
    private Employee generalManager;

    @ApiModelProperty(value = "地址", example = "广州市天河路123号")
    private String address;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
