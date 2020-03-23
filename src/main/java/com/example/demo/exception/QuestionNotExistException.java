package com.example.demo.exception;

public class QuestionNotExistException extends RuntimeException  {
    //不工作
    public QuestionNotExistException(){
        super("问题不存在!");
    }
}
