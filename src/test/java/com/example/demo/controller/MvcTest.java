package com.example.demo.controller;

import com.example.demo.model.User;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcBuilderSupport;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Slf4j
@SpringBootTest
public class MvcTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    //虚拟mvc请求，获取到处理结果
    MockMvc mockMvc;

    @Before
    //@Before –表示在任意使用@Test注解标注的public void方法执行之前执行
    public void initMockMvc(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 测试分页是否成功
     * @throws Exception
     */
    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .param("pageNum","8")).andReturn();
        //请求成功以后，请求域中有PageInfo，取出Pageinfo进行验证
        MockHttpServletRequest mockHttpServletRequest=mvcResult.getRequest();
        PageInfo pageInfo=(PageInfo) mockHttpServletRequest.getAttribute("pageInfo");
        log.info("当前页码:"+pageInfo.getPageNum());
        log.info("总页码："+pageInfo.getPages());
        log.info("总记录数："+pageInfo.getTotal());
        log.info("在页面需要连续显示的页码：");

        int[] NavigatePageNum =pageInfo.getNavigatepageNums();
        for(int i:NavigatePageNum){
            log.info(""+i);
        }
        //获取Users数据
        List<User> usersList=pageInfo.getList();
        log.info("当前页查询记录");
        for(User user:usersList){
            log.info("ID:"+user.getUserId()+"==>Name:"+user.getRole());
        }

    }
}
