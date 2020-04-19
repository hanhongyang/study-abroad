package com.example.demo;

import com.xnx3.aliyun.chatbot.ChatbotUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class CustomServiceTest {
    static ChatbotUtil chat;
    static{
        /*
         * 参数说明
         * accessKeyId 阿里云的 Access Key Id
         * accessKeySecret 阿里云的 Access Key Secret
         * chatbotUrl 云小蜜接口地址，传入如： https://chatbot.cn-shanghai.aliyuncs.com/   如果不懂，固定传入这个url即可
         * chatbotInstanceId 云小蜜的机器人ID，如： chatbot-cn-1234567890 。机器人实例ID。登录云小蜜控制台，左侧面板选择开发者->基本配置，查看机器人示例信息，可获得该实例ID。
         */
        chat = new ChatbotUtil("LTAI4G3wc7YKtEH645x1Y4BM", "V2nHKLpGyRNfU4tU1nBhKYkLaOO1jt", "https://chatbot.cn-shanghai.aliyuncs.com/", "chatbot-cn-eMmVv6Yfyb");
    }

    /**
     * 测试云小蜜
     * @throws IOException
     */
    @Test
    public void testMi() throws IOException {

        //chat创建后，可多次使用
        System.out.println(chat.question("你好").getText());
        //System.out.println(chat.question("怎么留学").getText());

    }
}
