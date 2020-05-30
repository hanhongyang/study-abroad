package com.example.demo.controller;

import com.example.demo.model.Msg;
import com.xnx3.aliyun.chatbot.ChatbotUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@Slf4j
public class ChatBotController {

    static ChatbotUtil chat;
    static{
        /*
         * 参数说明
         * accessKeyId 阿里云的 Access Key Id
         * accessKeySecret 阿里云的 Access Key Secret
         * chatbotUrl 云小蜜接口地址，传入如： https://chatbot.cn-shanghai.aliyuncs.com/   如果不懂，固定传入这个url即可
         * chatbotInstanceId 云小蜜的机器人ID，如： chatbot-cn-1234567890 。机器人实例ID。登录云小蜜控制台，左侧面板选择开发者->基本配置，查看机器人示例信息，可获得该实例ID。
         */
        chat = new ChatbotUtil("LTAI4GEHdtWLCB5XYBebJe9L", "lF32OdCNVc9TiT6BmYMCwgJdiomAKn", "https://chatbot.cn-shanghai.aliyuncs.com/", "chatbot-cn-eMmVv6Yfyb");
    }
    @ResponseBody
    @PostMapping("/chatbot")
    public Msg chatbot(@RequestParam(value = "question")String question){
        //chat创建后，可多次使用
        try {
            String answer=chat.question(question).getText();
            return Msg.success().add("answer",answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Msg.fail().add("answer","机器人出问题了！");
    }
}
