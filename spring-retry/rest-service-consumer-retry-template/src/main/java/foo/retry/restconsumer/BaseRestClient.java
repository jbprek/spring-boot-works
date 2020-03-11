package foo.retry.restconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class BaseRestClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RetryTemplate retryTemplate;

    @Value("${my.base.url}")
    private String baseEnpoint;

    public int invoke(int value) {
        try {
            retryTemplate.execute(w -> {
                        log.info("Retry Block, exception {}, retry {}", w.getLastThrowable(), w.getRetryCount());

                        ResponseEntity<?> res = restTemplate.getForEntity(baseEnpoint + value, String.class);
                        return res.getStatusCode().value();

                    },
                    q -> {
                        log.info("Recovery Block, exception {}, retry {}",
                                q.getLastThrowable().getClass().getCanonicalName(), q.getRetryCount());

                        return null;

                    });
            ResponseEntity<?> res = restTemplate.getForEntity(baseEnpoint + value, String.class);
            return res.getStatusCode().value();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return e.getRawStatusCode();
        }

    }

    private boolean isRetryableCondition(Throwable t) {
        if (t instanceof HttpClientErrorException && ((HttpClientErrorException) t).getRawStatusCode() == 400) {
            return true;
        } else {
            return false;
        }
    }


}
