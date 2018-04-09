package com.wixdom.bookcenter.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
@Data
@ApiModel(value = "仓库")
public class Warehouse extends Model<Warehouse> {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "名称", example = "商场")
    private String name;

    @ApiModelProperty(value = "管理员工号", example = "1")
    private Long managerEmployeeId;

    @ApiModelProperty(value = "电话")
    private String phone;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
