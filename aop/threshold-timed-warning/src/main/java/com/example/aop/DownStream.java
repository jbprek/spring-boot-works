package com.example.aop;

import com.example.aop.timedthresholdwarn.TimedThresholdWarning;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DownStream {

    @lombok.Data
    @AllArgsConstructor
    public static class Data {
        private String hello;
    }

    private AtomicInteger count = new AtomicInteger(0);

    @TimedThresholdWarning
    public Data serve()  {
        var countVal = count.incrementAndGet();
        if (countVal % 2 == 0) {
            try {
                Thread.sleep(1001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new Data("Hello " + countVal);
    }
}
