package com.gjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private String email;
    private double money;
    private String userImg;
    private String detailAddress;
    private String phone;
    private String receiveName;
}
