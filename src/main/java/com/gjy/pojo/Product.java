package com.gjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private double pprice;
    private String pimg;
    private int businessId;
    private int pnumber;
    private String ptitle;
    private Date createTime;
    private Date updateTime;
    private int psale;
    private String ptype;
}
