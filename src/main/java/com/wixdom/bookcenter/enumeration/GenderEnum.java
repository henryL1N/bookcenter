package com.wixdom.bookcenter.enumeration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "性别")
public enum GenderEnum {

    @ApiModelProperty(value = "男")
    MALE,

    @ApiModelProperty(value = "女")
    FEMALE

}