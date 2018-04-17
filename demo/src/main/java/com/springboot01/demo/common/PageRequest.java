package com.springboot01.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

/**
 * create by zzm on 2018/4/16
 **/
@ApiModel("分页参数")
public class PageRequest implements Serializable {
    @ApiModelProperty(value = "记录数")
    private Integer row;
    @ApiModelProperty(value = "当前页 从0开始")
    private Integer index;
    @ApiModelProperty(hidden=true)
    private Integer offset;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getOffset() {
        return index * row;
    }

    public PageRequest(Integer row, Integer index) {
        this.row = row;
        this.index = index;
    }
}
