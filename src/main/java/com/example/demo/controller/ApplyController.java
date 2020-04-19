package com.example.demo.controller;

import com.example.demo.model.Msg;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ApplyController {

    @GetMapping("/applyWizard")
    public String applyWizard(HttpSession httpSession,Model model){
        User user=(User)httpSession.getAttribute("user");
        model.addAttribute("recommender",user);
        return "apply/applyWizard";
    }
    //获取推荐人列表
    @GetMapping("/Recommenders")
    @ResponseBody
    public Msg Recommenders(HttpSession httpSession){
        User user=(User)httpSession.getAttribute("user");
        if(user!=null){
            //查询用户的推荐人

            List<User> users= new ArrayList();
            users.add(new User(001,"001",null,null,null,null,null,"001",null,null,null));
            users.add(new User(002,"001",null,null,null,null,null,"002",null,null,null));
            users.add(new User(003,"001",null,null,null,null,null,"003",null,null,null));
            return Msg.success().add("recommenders",users);
        }else {
            return Msg.fail();
        }

    }
}
