package com.foo;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/hello")
public class AppRestController {

    private AppConfProperties confProperties;

    @GetMapping
    public ResponseEntity<String> hello() {

        return ResponseEntity.ok(confProperties.getHelloMessage());
    }

    @GetMapping(path = "secret")
    public ResponseEntity<String> secret() {

        return ResponseEntity.ok(confProperties.getSecret());
    }


}
