package com.wixdom.bookcenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "分类")
public class Category {

    @ApiModelProperty(value = "名称", example = "工具书")
    private String name;

    @ApiModelProperty(value = "销售部门")
    private Department salesDepartment;

}
