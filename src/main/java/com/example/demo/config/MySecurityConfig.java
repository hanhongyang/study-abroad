package com.example.demo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //定制请求的授权规则
//        http.authorizeRequests().antMatchers("/").permitAll()
//        .antMatchers("/users").hasRole("1");
        //开启自动配置的登录功能
        //http.formLogin();
        //1、来到/login登录页
        //2、重定向到/login?error表示登录失败
    }


}
