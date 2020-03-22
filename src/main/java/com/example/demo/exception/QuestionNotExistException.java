package com.example.demo.exception;

public class QuestionNotExistException extends RuntimeException  {
    public QuestionNotExistException(){
        super("问题不存在!");
    }
}
