package src.main.java.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class Bird implements Pet{

    private String name;

    public Bird(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public String walk() {
        return "Bird " + name + " flies";
    }
}
