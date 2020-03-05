package foo.app;

import foo.app.service.AnotherService;
import foo.lib.HelloService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@AllArgsConstructor
@SpringBootApplication(scanBasePackages = {"foo.lib", "foo.app"})
public class BootAppApplication implements CommandLineRunner {

    private final HelloService component;

    private final AnotherService anotherService;

    public static void main(String[] args) {
        SpringApplication.run(BootAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(component.hello());
        log.info(anotherService.ping());
    }
}
