package com.springboot01.demo.common;

import java.io.Serializable;
import java.util.List;

/**
 * create by zzm on 2018/4/16
 **/
public class PageResponse<T>  implements Serializable{
    private int total;

    private List<T> rows;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
