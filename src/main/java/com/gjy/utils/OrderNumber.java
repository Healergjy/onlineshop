package com.gjy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNumber {
    public static String getOrderNum(){
        Random random = new Random();
        StringBuffer buffer=new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        buffer.append(sdf.format(new Date()));
        int j;
        for (int i = 0; i < 10; i++) {
            j=random.nextInt(10);
            buffer.append(j);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(OrderNumber.getOrderNum());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dates = sdf.format(date);
        System.out.println(dates);
    }
}
