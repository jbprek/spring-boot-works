package com.foo;

import com.foo.config.ConfigDemoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestContoller {

    @Autowired
    ConfigDemoProperties configDemoProperties;

    @GetMapping(path = "/hello")
    public ConfigDemoProperties getConfigDemoProperties() {
        return configDemoProperties;
    }
}
