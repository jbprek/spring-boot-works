package foo.app.service;

import org.springframework.stereotype.Service;

@Service
public class AnotherService {

    public String ping(){
        return "pong";
    }
}
