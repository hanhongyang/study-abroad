package com.example.demo.advice;

import com.alibaba.fastjson.JSON;
import com.example.demo.enums.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.exception.QuestionNotExistException;
import com.example.demo.exception.UserNotExistException;
import com.example.demo.model.Msg;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandeler {

    //处理用户不存在
    //不工作
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        //传入错误状态码，否则不会进入定制的错误页面
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notExist");
        map.put("message","用户不存在");
        request.setAttribute("extend",map);
        return "forward:/error";
    }

    //处理问题不存在
    //不工作
    @ExceptionHandler(QuestionNotExistException.class)
    public String handleQuestionNotExistException(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        //传入错误状态码，否则不会进入定制的错误页面
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","question.notExist");
        map.put("message","问题不存在");
        request.setAttribute("extend",map);
        return "forward:/error";
    }
    //自适应
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request, HttpServletResponse response, Model model) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            Msg msg;
            // 返回 JSON
            if (e instanceof CustomizeException) {
                msg = Msg.error((CustomizeException) e);
            } else {
                msg = Msg.error(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(msg));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        } else {
            //传入错误状态码，否则不会进入定制的错误页面
            request.setAttribute("javax.servlet.error.status_code",500);
            // 错误页面跳转
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return "forward:/error";
        }
    }
}
