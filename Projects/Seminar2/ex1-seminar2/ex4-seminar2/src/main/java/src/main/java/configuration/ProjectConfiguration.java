package src.main.java.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "src.main.java.model")
public class ProjectConfiguration {

    @Bean //Directly injected into person name
    public String personName(){
        return "Lucian";
    }

}
