package src.main.java.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import src.main.java.model.Bird;
import src.main.java.model.Cat;
import src.main.java.model.Person;
import src.main.java.model.Pet;

@Configuration
public class ProjectConfiguration {

    @Bean(name = "bird")
    public Pet bird(){
        Pet bird = new Bird("Parrot");
        return bird;
    }

    @Bean
    public Pet cat(){
        Pet pet = new Cat("Tom", 3);
        return pet;
    }

    @Bean
    public Person person(){
        Person person = new Person();
        person.setName("Andrei");
        person.setPet(cat());
        return person;
    }

    @Bean
    @Primary
    public Person person2(){
        Person person = new Person();
        person.setName("Cristian");
        person.setPet(bird());
        return person;
    }


}
