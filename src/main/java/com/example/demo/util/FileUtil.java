package com.example.demo.util;

import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    public static String fileUtil(MultipartFile file)throws Exception{
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("\\"));
        }
        // 获取文件存放地址
        String filePath = ClassUtils.getDefaultClassLoader().getResource("static/upload/").getPath();;

        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();// 不存在路径则进行创建
        }
        FileOutputStream out = null;

        // 重新自定义文件的名称
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String d = sdf.format(date);// 时间
        filePath = filePath + d + fileName;
        out = new FileOutputStream(filePath);
        out.write(file.getBytes());
        out.flush();
        out.close();
        return filePath;
    }
}
