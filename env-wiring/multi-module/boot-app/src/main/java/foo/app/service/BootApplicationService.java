package foo.app.service;

import org.springframework.stereotype.Service;

@Service
public class BootApplicationService {

    public static final String PONG = "pong";

    public String ping(){
        return PONG;
    }
}
