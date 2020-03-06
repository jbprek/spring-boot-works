package foo.app;

import foo.app.service.BootApplicationService;
import foo.lib.LibraryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@AllArgsConstructor
@SpringBootApplication/*(scanBasePackages = {"foo.lib", "foo.app"})*/
public class BootApplication implements CommandLineRunner {

    private final LibraryService component;

    private final BootApplicationService anotherService;

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(component.hello());
        log.info(anotherService.ping());
    }
}
