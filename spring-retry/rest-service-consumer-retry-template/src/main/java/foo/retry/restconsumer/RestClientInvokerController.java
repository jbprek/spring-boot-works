package foo.retry.restconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RestClientInvokerController {

    @Autowired
    private BaseRestClient client;

    @GetMapping("/invoker/{id}")
    ResponseEntity<Integer> invoke(@PathVariable("id") Integer arg) throws Exception {
        return ResponseEntity.ok(client.invoke(arg));
    }
}
