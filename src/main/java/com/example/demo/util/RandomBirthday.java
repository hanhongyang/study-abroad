package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成随机生日
 * parm："yyyy-MM-dd"格式的开始日期，结束日期
 * return：开始与结束之间的一个随机日期
 */
public class RandomBirthday {
    public static Date randomDate(String beginDate,String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            //只取日期"yyyy-MM-dd"，去掉时间
            Date date1=new java.sql.Date(new Date(date).getTime());
            return date1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//
    public static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }

}
