package com.example.demo.controller;

import com.example.demo.model.Msg;
import com.example.demo.model.Question;
import com.example.demo.model.Section;
import com.example.demo.model.User;
import com.example.demo.service.Impl.QuestionServiceImpl;
import com.example.demo.service.Impl.SectionServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@Slf4j
public class BBSController {

    @Autowired
    SectionServiceImpl sectionService;
    @Autowired
    QuestionServiceImpl questionService;

    /**
     * 所有板块页面
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/sections")
    public String BBS(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, Model model){
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pageNum,9);
        //startpage后紧跟的查询就是分页查询
        //获取所有板块携带学校信息
        List<Section> sectionList=sectionService.getAllWithSchool();
        //用pageinfo包装查询结果，只需要将pageinfo交给页面就行了
        PageInfo pageInfo=new PageInfo(sectionList);
        model.addAttribute("pageInfo",pageInfo);
        return "bbs/sections";
    }

    /**
     * 某个板块页面
     * @param sectionId
     * @param model
     * @return
     */
    @GetMapping("/section/{sectionId}")
    public String section(@PathVariable("sectionId") Integer sectionId,
                          @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                          Model model){
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pageNum,10);
        //startpage后紧跟的查询就是分页查询
        //获取某个板块内所有问题
        List<Question> questionList=questionService.getAllBySectionId(sectionId);
        //用pageinfo包装查询结果，只需要将pageinfo交给页面就行了
        PageInfo pageInfo=new PageInfo(questionList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("sectionId",sectionId);
        return "bbs/questions";
    }

    /**
     * 发布问题
     * @param
     * @return
     */
    @PostMapping("/question")
    @ResponseBody
    public Msg publishQuestion(@RequestParam(value = "title")String title,
                               @RequestParam(value = "description")String description,
                               @RequestParam(value = "tag")String tag,
                               @RequestParam(value = "sectionId")String sectionId,
                               HttpSession httpSession
                               ){
        Long gmtCreate=System.currentTimeMillis();
        Long gmtModify=gmtCreate;
        User user=(User)httpSession.getAttribute("user");
        if(user!=null){
            questionService.publish(title,description,gmtCreate,gmtModify,user.getUserId(),tag,Integer.parseInt(sectionId));
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }
}
