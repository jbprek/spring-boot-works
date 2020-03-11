package foo.retry.restservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller emulating external service
 */
@RestController
public class BaseRestController {

    @GetMapping("/base/{id}")
    public ResponseEntity<?> error(@PathVariable("id") String arg) {
        if (arg.equals("1")) {
            return ResponseEntity.ok("OK");
        } else if (arg.equals("2")) {
            return ResponseEntity.badRequest().build();
        } else if (arg.equals("3")) {
            return ResponseEntity.status(503).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
