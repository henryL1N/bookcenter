package com.wixdom.bookcenter.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Henry Lin badcop@163.com
 */
@ApiModel(value = "分类")
public class Category extends Model<Category> {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "名称", example = "工具书")
    private String name;

    @ApiModelProperty(value = "销售部门Id", example = "1")
    private Long salesDepartmentId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
