package foo.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloComponent {

    @Autowired
    private HelloConfiguration config;

    public String hello() {
        return "Hello from " + config.getName();
    }

}
