package src.main.java.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import src.main.java.model.Person;
import src.main.java.model.Pet;

@Configuration
public class ProjectConfiguration {

    @Bean
    public Person person1(){
        Person person = new Person();
        person.setName("Cristian");
        return person;
    }

    @Bean
    public Pet pet1(){
        Pet pet = new Pet();
        pet.setAge("2");
        pet.setName("Tom");
        return pet;
    }

    @Bean Person person2(){
        Person person = new Person();
        person.setPet(pet1());
        person.setName("Mihai");
        return person;
    }

}
