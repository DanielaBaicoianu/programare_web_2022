package src.main.java.model;

import org.springframework.stereotype.Component;

import static src.main.java.constants.ProjectConstants.CAT_WALK;

@Component
public class Cat implements Pet {

    private String name = "Alfie";

    private int age = 3;


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public String walk() {
        return CAT_WALK;
    }
}
