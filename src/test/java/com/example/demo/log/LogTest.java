package com.example.demo.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {

    //记录器
    //Logger logger= LoggerFactory.getLogger(getClass());使用@Slf4j后就不用此行代码了
    @Test
    public void testLog(){

        //日志的级别；
        //由低到高   trace<debug<info<warn<error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
    log.trace("这是trace日志");
    log.debug("这是debug日志");
//SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
    log.info("这是info日志");
    log.warn("这是warn日志");
    log.error("这是error日志");

    }
}
