package com.example.demo.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;
import org.springframework.util.ClassUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.UUID;
@Slf4j
public class PicUtil {
    public static String pictureUtil(String base64Data){
        String dataPrix = "";
        String data = "";

        String[] d = base64Data.split("base64,");
        if (d != null && d.length == 2) {
            dataPrix = d[0];
            data = d[1];
        }
        String suffix = "";
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {//data:image/jpeg;base64,base64编码的jpeg图片数据
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {//data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {//data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {//data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }
        String imgName = UUID.randomUUID().toString();
        String tempFileName = imgName + suffix;
        byte[] bs = Base64Utils.decodeFromString(data);
        String url = ClassUtils.getDefaultClassLoader().getResource("static/images/icons").getPath();
        try {
            FileUtils.writeByteArrayToFile(new File(url + "/" + tempFileName), bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String picture="/images/icons/"+tempFileName;
        return picture;

    }


    /**
     * 从网上下载图片并保存到本地,返回本地保存地址
     * @param iconUrl
     * @throws Exception
     */
    public static String saveIcon (String iconUrl) throws Exception {
        //new一个URL对象
        URL url = new URL(iconUrl);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        //获取要存放图片的本地地址
        String saveUrl = ClassUtils.getDefaultClassLoader().getResource("static/images/icons").getPath();
        //给图片文件起名字
        String imgName = UUID.randomUUID().toString();
        //写入文件
        FileUtils.writeByteArrayToFile(new File(saveUrl + "/" +imgName+".png" ), data);
        String imgSaveUrl="/images/icons/"+imgName+".png";
        return imgSaveUrl;
    }

    /**
     * 通过输入流获取图片数据转换为二进制数据
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
        //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
