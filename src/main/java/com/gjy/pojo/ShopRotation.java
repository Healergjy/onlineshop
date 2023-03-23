package com.gjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopRotation {
    private int id;
    private int businessId;
    private String frotationImg;
    private String srotationImg;
    private String trotationImg;
}
