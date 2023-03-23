package com.gjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Business {
    private int id;
    private String username;
    private String password;
    private double money;
    private String userImg;
    private String email;
    private Date createTime;
    private Date updateTime;
}
