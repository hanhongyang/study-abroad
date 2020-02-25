package com.example.demo.controller;

import com.example.demo.model.Msg;
import com.example.demo.model.User;
import com.example.demo.service.Impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
    /**
     * 查询用户数据。分页查询
     * @return 用户列表
     */
    @GetMapping("/users")
    public String getAllUsers(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, Model model){
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pageNum,10);
        //startpage后紧跟的查询就是分页查询
        List<User> users=userService.getAll();
        //用pageinfo包装查询结果，只需要将pageinfo交给页面就行了
        PageInfo pageInfo=new PageInfo(users);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/users";
    }
    /**
     * 保存user
     * @return
     */
    @PostMapping("/user")
    @ResponseBody
    public Msg saveUser(@RequestParam(value = "rule",defaultValue = "0")String rule,
                        @RequestParam(value = "password")String password,
                        @RequestParam(value = "countryId")String countryId,
                        @RequestParam(value = "email")String email,
                        @RequestParam(value = "mobile")String mobile,
                        @RequestParam(value = "birthday",defaultValue = "")String birthday,
                        @RequestParam(value = "name")String name
                        ){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            //判断生日是否为空
            if("".equals(birthday)){
                User user=new User(null,password,Integer.parseInt(rule),Integer.parseInt(countryId),email,mobile,null,name);
                userService.save(user);
                return Msg.success();
            }else {
                Date birthday2=format.parse(birthday);
                User user=new User(null,password,Integer.parseInt(rule),Integer.parseInt(countryId),email,mobile,birthday2,name);
                userService.save(user);
                return Msg.success();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.fail();
    }

    /**
     * 检验email是否可用
     * @param email
     * @return
     */
    @ResponseBody
    @PostMapping("/checkEmail")
    public Msg checkEmail(@RequestParam(value = "email") String email){
        //had保存是否email已存在
        boolean had=userService.checkEmail(email);
        if(had){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}
