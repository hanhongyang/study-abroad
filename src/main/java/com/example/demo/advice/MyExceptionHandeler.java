package com.example.demo.advice;

import com.example.demo.exception.QuestionNotExistException;
import com.example.demo.exception.UserNotExistException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandeler {

    //处理用户不存在
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        //传入错误状态码，否则不会进入定制的错误页面
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notExist");
        map.put("message","用户不存在");
        request.setAttribute("extend",map);
        return "forward；/error";
    }

    //处理问题不存在
    @ExceptionHandler(QuestionNotExistException.class)
    public String handleQuestionNotExistException(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        //传入错误状态码，否则不会进入定制的错误页面
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","question.notExist");
        map.put("message","问题不存在");
        request.setAttribute("extend",map);
        return "forward；/error";
    }
}
