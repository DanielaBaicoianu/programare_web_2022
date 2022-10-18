package src.main.java.model;

import org.springframework.stereotype.Component;

@Component
public class Bird implements Pet{

    private String name = "Parrot";

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
