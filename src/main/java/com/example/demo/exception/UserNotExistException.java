package com.example.demo.exception;

public class UserNotExistException extends RuntimeException {
    //不工作
    public UserNotExistException(){
        super("用户不存在");
    }
}
