package foo.app;

import foo.lib.HelloComponent;
import foo.lib.HelloConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(basePackages = {"foo.lib"})
@EnableConfigurationProperties(HelloConfiguration.class)
@SpringBootApplication
public class BootAppApplication implements CommandLineRunner {

    @Autowired
    private HelloComponent component;

    public static void main(String[] args) {
        SpringApplication.run(BootAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(component.hello());
    }
}
