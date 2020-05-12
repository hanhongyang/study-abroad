package com.example.demo.controller;

import com.example.demo.model.Application;
import com.example.demo.model.Msg;
import com.example.demo.model.User;
import com.example.demo.service.Impl.ApplicationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ApplyController {
    @Autowired
    ApplicationServiceImpl applicationService;

    @GetMapping("/applyWizard")
    public String applyWizard(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("recommender", user);
        return "apply/applyWizard";
    }

    //获取推荐人列表
    @GetMapping("/Recommenders")
    @ResponseBody
    public Msg Recommenders(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            //查询用户的推荐人

            List<User> users = new ArrayList();
            users.add(new User(001, "001", null, null, null, null, null, "001", null, null, null));
            users.add(new User(002, "001", null, null, null, null, null, "002", null, null, null));
            users.add(new User(003, "001", null, null, null, null, null, "003", null, null, null));
            return Msg.success().add("recommenders", users);
        } else {
            return Msg.fail();
        }

    }
    //映射未成功
    //申请留学-第一步
    @PostMapping("/ApplyStep1")
    @ResponseBody
    public Msg saveApply1(HttpSession httpSession,
                          @RequestParam(value = "schoolId") String schoolId,
                          @RequestParam(value = "majorId") String majorId,
                          @RequestParam(value = "batch") String batch,
                          @RequestParam(value = "applyType") String applyType) {
        User user = (User) httpSession.getAttribute("user");

        if(user!=null){
            Application application = new Application(user.getUserId(),
                    Integer.parseInt(schoolId),
                    Integer.parseInt(majorId),
                    0,
                    batch,
                    Integer.parseInt(applyType),
                    1);
            applicationService.saveApplyStep1(application);
            //获取刚插入的申请的id
            int id = application.getId();

            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    //申请留学-第三步
    @PutMapping("/ApplyStep3")
    @ResponseBody
    public Msg saveApply3() {

        return Msg.success();
    }

    //测试上传
    @PostMapping("/upload")
    @ResponseBody
    public Msg upload(HttpServletRequest request, HttpServletResponse response
            , @RequestParam("file") MultipartFile[] file) throws Exception {


        return null;
    }
}


