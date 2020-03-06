package foo.app;

import static org.assertj.core.api.Assertions.assertThat;

import foo.app.service.BootApplicationService;
import foo.lib.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootAppApplicationTests {

    @Autowired
    private BootApplicationService serviceInApplication;

    @Autowired
    private LibraryService serviceInLibrary;

    @Test
    void contextLoads() {
        assertThat(serviceInLibrary.hello()).isNotNull();
        assertThat(serviceInApplication.ping()).isEqualTo("pong");
    }

}
