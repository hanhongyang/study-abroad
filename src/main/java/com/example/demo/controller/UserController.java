package com.example.demo.controller;

import com.example.demo.model.Msg;
import com.example.demo.model.User;
import com.example.demo.service.Impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
//    @GetMapping("/admin")
//    public String admin(){
//        return "admin/users";
//    }
//    /**
//     * 查询用户数据返回json。分页查询
//     * @return 用户列表
//     */
//    @GetMapping("/users")
//    @ResponseBody
//    public Msg getAllusersWithJson(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
//        //在查询之前只需要调用，传入页码，以及每页的大小
//        PageHelper.startPage(pageNum,5);
//        //startpage后紧跟的查询就是分页查询
//        List<User> users=userService.getAll();
//        //用pageinfo包装查询结果，只需要将pageinfo交给页面就行了
//        PageInfo pageInfo=new PageInfo(users);
//        return Msg.success().add("pageInfo",pageInfo);
//
//    }
    /**
     * 查询用户数据。分页查询
     * @return 用户列表
     */
    @GetMapping("/users")
    public String getAllUsers(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, Model model){
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pageNum,5);
        //startpage后紧跟的查询就是分页查询
        List<User> users=userService.getAll();
        //用pageinfo包装查询结果，只需要将pageinfo交给页面就行了
        PageInfo pageInfo=new PageInfo(users);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/users-2";
    }
}
