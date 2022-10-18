package src.main.java.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import src.main.java.model.Person;
import src.main.java.model.Pet;

@ComponentScan(basePackages = "src.main.java.model")
@Configuration
public class ProjectConfiguration {
}
