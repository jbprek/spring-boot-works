package com.example.aop;

import com.example.aop.timedthresholdwarn.TimedThresholdWarning;
import org.springframework.stereotype.Component;

@Component
public class TestDownStream {

    @TimedThresholdWarning(thresholdMillis = 50, messageTag = "DELAYED DownStream serveMethod")
    public void serveMethod(long delay){
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @TimedThresholdWarning(thresholdMillis = 60, messageTag = "DELAYED DownStream serveMethodThrowingException")
    public void serveMethodThrowingException(long delay){
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("DownStream Error");
    }
}
