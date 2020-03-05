package com.example.demo.controller;

import cn.hutool.json.JSONUtil;
import com.example.demo.model.Msg;
import com.example.demo.model.User;
import com.example.demo.service.Impl.UserServiceImpl;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@Controller
public class LoginController {
    private final AuthRequestFactory factory;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public Msg login(@RequestParam("userId")Integer userId,
                        @RequestParam("password")String password){
        log.info(password+userId);
    User user=userService.login(userId,password);
    if(user!=null){
        return Msg.success();
    }else {
        return Msg.fail();
    }
    }

    @GetMapping
    public List<String> list() {
        return factory.oauthList();
    }

    @GetMapping("/oauth/login/{type}")
    public void login(@PathVariable String type, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);

        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }
    @RequestMapping("/oauth/{type}/callback")
    public String login(@PathVariable String type, AuthCallback callback, Model model) {
        AuthRequest authRequest = factory.get(type);
        AuthResponse response = authRequest.login(callback);
        log.info("【response】= {}", JSONUtil.toJsonStr(response));
        //取出response包含的AuthUser
        AuthUser authUser=(AuthUser)response.getData();
        User user=userService.githubLogin(authUser.getUuid(),authUser.getNickname(),authUser.getAvatar());
        model.addAttribute("user",user);
        return "forward:/";
    }


}
