package foo.lib;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@ConfigurationProperties(prefix = "foo.lib.hello")
@Validated
@Data
public class LibraryProperties {

    @NotBlank
    private String name;

}
