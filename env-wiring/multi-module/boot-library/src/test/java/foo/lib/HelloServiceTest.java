package foo.lib;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest("foo.lib.hello.name=John")
public class HelloServiceTest {

	@Autowired
	private HelloService myService;

	@Test
	public void contextLoads() {
		assertThat(myService.hello()).isNotNull();
	}

	@SpringBootApplication
	static class TestConfiguration {
	}

}