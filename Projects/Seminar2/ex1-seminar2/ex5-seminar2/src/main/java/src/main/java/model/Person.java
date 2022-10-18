package src.main.java.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Value("${person.name}")
    private String name;

    @Autowired
    @Qualifier("bird")
    private Pet pet;

    public Person(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Pet getPet() {
        return pet;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", pet=" + pet.toString() +
                '}';
    }
}
