package com.example.demo.enums;

public enum UserRoleEnum {
    SUPER_ADMIN(1),//超级管理员
    SCHOOL_ADMIN(2),//学校管理员
    MY_USER(0),//本地普通用户
    GITHUB_USER(3);//github用户
    private Integer role;

    public Integer getRole() {
        return role;
    }

    UserRoleEnum(Integer role){
        this.role=role;
    }
}
