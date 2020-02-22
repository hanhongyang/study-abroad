package com.example.demo.util;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class randomBirthdayTest {

    @Test
    void randomDate() {
        for(int i=0;i<30;i++){
            Date date=randomBirthday.randomDate("1960-01-01","2010-12-31");
            System.out.println(date);
        }
        //format()方法将Date转换成指定格式的String
        //String dateStr = simpleDateFormat.format(date);
        //System.out.println(dateStr);//2018-08-24 15:37:47:033
        //调用parse()方法时 注意 传入的格式必须符合simpleDateFormat对象的格式，即"yyyy-MM-dd HH:mm:ss:SSS" 否则会报错！！
        //String string = "2018-8-24 12:50:20:545";
        //Date date2 = simpleDateFormat.parse(string);
    }
}