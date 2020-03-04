package com.example.demo.controller;

import com.example.demo.model.Msg;
import com.example.demo.model.User;
import com.example.demo.service.Impl.UserServiceImpl;
import com.example.demo.util.PicUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.*;
@Slf4j
@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Value("${userIconDefaultUrl}")
    private String DefaultIcon;
    /**
     * 查询用户数据。分页查询
     * @return 用户列表
     */
    @GetMapping("/users")
    public String getAllUsers(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, Model model){
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pageNum,10);
        //startpage后紧跟的查询就是分页查询
        List<User> users=userService.getAllWithCountry();
        //用pageinfo包装查询结果，只需要将pageinfo交给页面就行了
        PageInfo pageInfo=new PageInfo(users);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/users";
    }

    /**
     * 查询User
     * @param userId
     * @return查到的User
     */
    @GetMapping("/user/{userId}")
    @ResponseBody
    public Msg getUser(@PathVariable("userId")Integer userId){

        User user=userService.getUser(userId);
        return Msg.success().add("user",user);
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
                User user=new User(null,password,Integer.parseInt(rule),Integer.parseInt(countryId),email,mobile,null,name,DefaultIcon);
                userService.save(user);
                return Msg.success();
            }else {
                Date birthday2=format.parse(birthday);
                User user=new User(null,password,Integer.parseInt(rule),Integer.parseInt(countryId),email,mobile,birthday2,name,DefaultIcon);
                userService.save(user);
                return Msg.success();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.fail();
    }

    @ResponseBody
    @PutMapping("/user/{userId}")
    public Msg updateUser(@PathVariable(value = "userId")Integer userId,
                          @RequestParam(value = "rule",defaultValue = "0")String rule,
                          @RequestParam(value = "password")String password,
                          @RequestParam(value = "countryId")String countryId,
                          @RequestParam(value = "email")String email,
                          @RequestParam(value = "mobile")String mobile,
                          @RequestParam(value = "birthday",defaultValue = "")String birthday,
                          @RequestParam(value = "name")String name,
                          @RequestParam(value = "icon")String icon){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //判断头像是否修改
        if("data".equals(icon.substring(0,4))) {//本地上传的图片
            //截取icon路径
            String iconUrl = "";
            iconUrl = PicUtil.pictureUtil(icon);
            icon = iconUrl;
        }else {
            log.info("没有修改头像");
        }
        try{
            //判断生日是否为空
            if("".equals(birthday)){
                User user=new User(userId,password,Integer.parseInt(rule),Integer.parseInt(countryId),email,mobile,null,name,icon);
                userService.updateUser(user);
                return Msg.success();
            }else {
                Date birthday2=format.parse(birthday);
                User user=new User(userId,password,Integer.parseInt(rule),Integer.parseInt(countryId),email,mobile,birthday2,name,icon);
                userService.updateUser(user);
                return Msg.success();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.fail();
    }

    /**
     * 单个或批量根据userId删除user
     * 批量：1,2,3
     * 单个：1
     * @param
     * @return
     */
    @ResponseBody
    @DeleteMapping("/user/{userIds}")
    public Msg deleteByUserId(@PathVariable(value = "userIds") String userIds){
        if(userIds.contains(",")){
            List<Integer> userIdList=new ArrayList<>();
            String[] userIdsStr=userIds.split(",");
            for(String str:userIdsStr){
                userIdList.add(Integer.parseInt(str));
            }
            userService.batchDelete(userIdList);
        }else {
            Integer userId=Integer.parseInt(userIds);
            userService.deleteByUserId(userId);
        }

        return Msg.success();
    }
    /**
     * 检验email是否可用
     * @param email
     * @return
     */
    @ResponseBody
    @PostMapping("/checkEmail")
    public Msg checkEmail(@RequestParam(value = "email") String email){
        //后端校验表单数据
        String regEmail="(^[A-Za-z0-9]+([_\\.][A-Za-z0-9]+)*@([A-Za-z0-9\\-]+\\.)+[A-Za-z]{2,6}$)";
        if(!email.matches(regEmail)){
            return Msg.fail().add("va_msg","邮箱格式不正确");
        }
        //had保存是否email已存在
        boolean had=userService.checkEmail(email);
        if(had){
            return Msg.success();
        }else {
            return Msg.fail().add("va_msg","email不可用");
        }
    }
}
