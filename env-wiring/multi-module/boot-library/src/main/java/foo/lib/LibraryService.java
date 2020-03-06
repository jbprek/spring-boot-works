package foo.lib;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(LibraryProperties.class)
@AllArgsConstructor
public class LibraryService {

    private final LibraryProperties config;

    public String hello() {
        return "Hello from " + config.getName();
    }

}
