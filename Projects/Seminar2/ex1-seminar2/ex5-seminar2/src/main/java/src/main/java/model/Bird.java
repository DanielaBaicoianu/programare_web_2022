package src.main.java.model;

import org.springframework.stereotype.Component;

import static src.main.java.constants.ProjectConstants.BIRD_WALK;

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
        return BIRD_WALK;
    }
}
