package foo.lib;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// TODO check why I get an error without

@ConfigurationProperties(prefix = "foo.lib.hello")
@Data
public class HelloConfiguration {

    private String name;

}
