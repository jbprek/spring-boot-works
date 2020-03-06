package foo.lib;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LibraryProperties configurationProperties(){
        return new LibraryProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public LibraryService helloService(){
        return new LibraryService(configurationProperties());
    }

}
