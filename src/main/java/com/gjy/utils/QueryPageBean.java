package com.gjy.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPageBean {
    private Integer currentPage;//当前页
    private Integer pageSize;//每页记录数
    private String queryString;//查询条件
}
