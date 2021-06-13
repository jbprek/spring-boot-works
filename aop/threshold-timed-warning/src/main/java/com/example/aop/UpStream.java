package com.example.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/hello", produces = "application/json")
public class UpStream {

    @Autowired
    private DownStream downStream;

    @GetMapping
    public ResponseEntity<DownStream.Data> hello() {
        return ResponseEntity.ok(downStream.serve());
    }

}
