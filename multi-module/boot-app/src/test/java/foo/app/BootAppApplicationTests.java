package foo.app;

import static org.assertj.core.api.Assertions.assertThat;

import foo.app.service.AnotherService;
import foo.lib.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootAppApplicationTests {

    @Autowired
    private AnotherService serviceInApplication;

    @Autowired
    private HelloService serviceInLibrary;

    @Test
    void contextLoads() {
        assertThat(serviceInLibrary.hello()).isNotNull();
        assertThat(serviceInApplication.ping()).isEqualTo("pong");
    }

}
