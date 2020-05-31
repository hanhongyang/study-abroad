package com.example.demo.controller;

import com.example.demo.enums.NotificationStatusEnum;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.model.*;
import com.example.demo.service.Impl.ApplicationServiceImpl;
import com.example.demo.service.Impl.NotificationServiceImpl;
import com.example.demo.service.Impl.SchoolServiceImpl;
import com.example.demo.util.FPTree;
import com.example.demo.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
public class ApplyController {
    @Autowired
    ApplicationServiceImpl applicationService;
    @Autowired
    NotificationServiceImpl NotificationService;
    @Autowired
    SchoolServiceImpl schoolService;
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
            Application application = new Application();
            application.setUserId(user.getUserId());
            application.setSchoolId(Integer.parseInt(schoolId));
            application.setMajorId(Integer.parseInt(majorId));
            application.setStauts(0);
            application.setBatch(batch);
            application.setApplyType(Integer.parseInt(applyType));
            application.setStep(1);
            applicationService.saveApplyStep1(application);
            //获取刚插入的申请的id
            int id = application.getId();
            return Msg.success().add("id",id);
        }else {
            return Msg.fail();
        }
    }
    //申请留学-第二步
    @PostMapping("/ApplyStep2")
    @ResponseBody
    public Msg saveApply2(HttpSession httpSession,
                          @RequestParam(value = "id") String id,
                          @RequestParam(value = "name",required = false) String name,
                          @RequestParam(value = "gender",required = false) String gender,
                          @RequestParam(value = "maritalStatus",required = false) String maritalStatus,
                          @RequestParam(value = "birthday",required = false) String birthday,
                          @RequestParam(value = "passport",required = false) String passport,
                          @RequestParam(value = "email",required = false) String email,
                          @RequestParam(value = "countryId",required = false) String countryId,
                          @RequestParam(value = "phone",required = false) String phone,
                          @RequestParam(value = "recommender",required = false) String recommender,
                          @RequestParam(value = "highSchool",required = false) String highSchool,
                          @RequestParam(value = "gradeNumber",required = false) String gradeNumber,
                          @RequestParam(value = "gradeRanking",required = false) String gradeRanking,
                          @RequestParam(value = "examType",required = false) String examType,
                          @RequestParam(value = "examTime",required = false) String examTime,
                          @RequestParam(value = "totalScore",required = false) String totalScore
                          ) {
        System.out.println(id);
        //转换日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        User user = (User) httpSession.getAttribute("user");
        if(user!=null){
            Application application = new Application();
            application.setId(Integer.parseInt(id));
            application.setName(name);
            application.setGender(Integer.parseInt(gender));
            application.setMaritalStatus(Integer.parseInt(maritalStatus));
            Date birthday2= null;
            try {
                birthday2 = format.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            application.setBirthday(birthday2);
            application.setPassport(passport);
            application.setEmail(email);
            application.setCountryId(Integer.parseInt(countryId));
            application.setPhone(phone);
            application.setRecommender(recommender);
            application.setHighSchool(highSchool);
            application.setGradeNumber(Integer.parseInt(gradeNumber));
            application.setGradeRanking(Integer.parseInt(gradeRanking));
            Date examTime2=null;
            try {
                examTime2 = format.parse(examTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            application.setExamTime(examTime2);
            application.setExamType(examType);
            application.setTotalScore(Integer.parseInt(totalScore));
            applicationService.saveApplyStep2(application);
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    //申请留学-第三步
    @PutMapping("/ApplyStep3")
    @ResponseBody
    public Msg saveApply3(@RequestParam("id")String id, HttpServletRequest request, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user!=null) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> multipartFiles = new ArrayList<>();
            for (int i = 0 ; i < 2 ; i++) {
                multipartFiles.addAll(multipartRequest.getFiles("temp" + i));
            }
            for(MultipartFile file:multipartFiles)
                try {
                    String filePath = FileUtil.fileUtil(file);
                    applicationService.saveApplyStep3(Integer.parseInt(id),filePath);
                    System.out.println(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Msg.fail();
                }
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    //推荐引擎
    @GetMapping("/RecommendedSchools")
    @ResponseBody
    public Msg t(@RequestParam(value = "schoolId")String schoolId,HttpSession httpSession){
        FPTree fptree = new FPTree();
        //设置最小支持度
        fptree.setMinSup(2);
        //读取数据库申请留学记录
        List<List<String>> transRecords = applicationService.records();
        System.out.println(transRecords);
        System.out.println("读取数据完毕！--------------------------------------------");
        //构造频繁1项集
        ArrayList<TreeNode> F1 = fptree.buildF1Items(transRecords);
        fptree.printF1(F1);
        System.out.println("找出频繁1项集完毕！----------------------------------------");
        //建立FP树
        TreeNode treeroot = fptree.buildFPTree(transRecords, F1);
        //打印FP-Tree
        fptree.printFPTree(treeroot);
        System.out.println("创建树完毕！----------------------------------------------");
        //从FPTree中找到所有的频繁模式
        Map<List<String>, Integer> patterns = fptree.findFP(treeroot, F1);
        System.out.println(patterns);
        //打印最终找到的所有频繁模式集
        //fptree.printFreqPatterns(patterns);
        System.out.println("找出频繁项完毕---------------------------------------------！");
        //设置置信度
        int confidence=2;
        //推荐的学校
        String recommendSchool;
        //找出包含申请学校的频繁项集
        Set<Map.Entry<List<String>, Integer>> ss = patterns.entrySet();
        Set<String> recommendSchoolSet=new HashSet<>();
        for (Map.Entry<List<String>, Integer> entry : ss) {
            List<String> list = entry.getKey();
            for (String item : list) {
                //判断是否包含申请学校
                if(item.equals(schoolId)){
                    //判断是否达到置信度阈值
                    if(entry.getValue()*confidence>Integer.parseInt(schoolId)){
                        for(int i=0;i<list.size();i++){
                            if(i== list.indexOf(item)){
                            }else {
                                recommendSchoolSet.add(list.get(i));
                                //System.out.println(list.get(i)+"为可推荐学校---------------------------------------------！");
                            }
                        }
                    }
                }
            }
        }
        //打印set
        Iterator<String> it = recommendSchoolSet.iterator();
        //记录通知内容
        StringBuffer content=new StringBuffer();
        while (it.hasNext()) {
            String str = it.next();
            content.append(schoolService.getById(Integer.parseInt(str)).getName()+'。');
            System.out.println(str+"为可推荐学校---------------------------------------------！");
        }

        //判断是否登录
        User user=(User)httpSession.getAttribute("user");
        if(user!=null) {
            Long gmtCreate = System.currentTimeMillis();
            //生成推荐通知
            Notification recommendNotification = new Notification(
                    NotificationTypeEnum.RECOMMEND_SCHOOLS.getType(),
                    user.getUserId(),
                    gmtCreate,
                    NotificationStatusEnum.UNREAD.getStatus(),
                    NotificationTypeEnum.nameOfType(NotificationTypeEnum.RECOMMEND_SCHOOLS.getType()),
                    content.toString());
            NotificationService.createNotify(recommendNotification);
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    //测试上传
    @PostMapping("/upload")
    @ResponseBody
    public Msg upload(@RequestParam("id")int id,@RequestParam("file") MultipartFile[] files)  {
        for(MultipartFile file:files){
            try {
                String filePath=FileUtil.fileUtil(file);
                applicationService.saveApplyStep3(id,filePath);
                System.out.println(filePath);
                return Msg.success();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Msg.success();
    }
}


