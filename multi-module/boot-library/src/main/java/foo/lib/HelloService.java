package foo.lib;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(HelloConfiguration.class)
@AllArgsConstructor
public class HelloService {

    private final HelloConfiguration config;

    public String hello() {
        return "Hello from " + config.getName();
    }

}
