package com.gjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopSetting {
    private int id;
    private String shopName;
    private int businessId;
    private String shopLogo;
    private String shopIntroduce;
    private String shopDescription;
    private String shopAddress;
}
