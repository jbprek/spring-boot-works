package com.example.aop;

import com.example.aop.timedthresholdwarn.TimedThresholdWarning;
import com.example.aop.timedthresholdwarn.TimedThresholdWarningAspect;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { TestDownStream.class,
        TimedThresholdWarningAspect.class,
        AnnotationAwareAspectJAutoProxyCreator.class })
class DemoApplicationTests {

    @Autowired
    private TestDownStream testDownStream;

    @Test
    void contextLoads() {
    }

    @Test
    void callServerNoDelay() {
        testDownStream.serveMethod(1);
    }

    @Test
    void callServerWithDelay() {
        testDownStream.serveMethod(51);
    }

}
