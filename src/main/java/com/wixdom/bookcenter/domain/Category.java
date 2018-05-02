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
@ApiModel(value = "分类")
@Entity
public class Category extends AbstractPersistable<Long> {

    @ApiModelProperty(value = "名称", example = "工具书")
    private String name;

    @ApiModelProperty(value = "销售部门")
    @OneToOne
    private Department salesDepartment;

}
