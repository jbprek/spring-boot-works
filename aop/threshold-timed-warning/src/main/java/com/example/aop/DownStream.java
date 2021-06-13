package com.example.aop;

import com.example.aop.timedthresholdwarn.TimedThresholdWarning;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DownStream {

    @Data
    @AllArgsConstructor
    public static class Response {
        private String hello;
    }

    private AtomicInteger count = new AtomicInteger(0);

    @TimedThresholdWarning(thresholdMillis = 500, messageTag = "DELAYED DownStream Response")
    public Response serve()  {
        var countVal = count.incrementAndGet();
        if (countVal % 3 == 0) {
            try {
                Thread.sleep(501);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("DownStream Error");

        }
        if (countVal % 2 == 0) {
            try {
                Thread.sleep(1001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new Response("Hello " + countVal);
    }
}
