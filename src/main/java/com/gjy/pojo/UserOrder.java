package com.gjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrder {
    private int id;
    private String orderId;
    private String status;
    private int userId;
    private int businessId;
    private double price;
    private String pimg;
    private String ptitle;
    private Date createTime;
    private Date updateTime;
    private Date payTime;
}
