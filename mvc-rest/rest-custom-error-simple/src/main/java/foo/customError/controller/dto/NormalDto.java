package foo.customError.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(staticName = "of")
public class NormalDto {
    private String value = "Hello World!";
}
