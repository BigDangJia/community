package com.peng.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class LoggerTests {
    private static final Logger logger = LoggerFactory.getLogger(LoggerTests.class); //我们需要使用Logger工厂对象，根据测试类的class文件,创建出来一个对象

    @Test
    public void testLogger() {
        System.out.println(logger.getName());

        logger.debug("deBug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");

    }

}
