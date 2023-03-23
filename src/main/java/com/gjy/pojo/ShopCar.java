package com.gjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCar {
    private int id;
    private int userId;
    private String ptitle;
    private String pimg;
    private int businessId;
    private double pprice;
    private Date createTime;
}
