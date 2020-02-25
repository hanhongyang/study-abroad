package com.example.demo.util;

import com.example.demo.mapper.CountryMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class ReadFile {

    /**
     * 把txt文件转为string
     */
   public static String txt2String(File file){
       StringBuilder result = new StringBuilder();
       try{
           BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
           String s = null;
           while((s = br.readLine())!=null){//使用readLine方法，一次读一行
               //System.lineSeparator()换行
               result.append(System.lineSeparator()+s);
           }
           br.close();
       }catch(Exception e){
           e.printStackTrace();
       }
       return result.toString();
   }


    public static void regionsToMysql(File file){

    }
}
