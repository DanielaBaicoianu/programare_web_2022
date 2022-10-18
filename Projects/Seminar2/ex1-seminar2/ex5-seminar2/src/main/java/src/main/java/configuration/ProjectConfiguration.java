package src.main.java.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "src.main.java.model")
@PropertySource("classpath:application.properties")
public class ProjectConfiguration {

    @Bean
    public String personName(){
        return "Lucian";
    }

}
