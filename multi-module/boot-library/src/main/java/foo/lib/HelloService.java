package foo.lib;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(HelloConfigurationProperties.class)
@AllArgsConstructor
public class HelloService {

    private final HelloConfigurationProperties config;

    public String hello() {
        return "Hello from " + config.getName();
    }

}
