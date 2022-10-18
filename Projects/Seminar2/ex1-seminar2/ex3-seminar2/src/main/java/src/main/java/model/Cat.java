package src.main.java.model;

import org.springframework.stereotype.Component;

public class Cat implements Pet{

    private String name ;

    private int age;

    public Cat(String name, int age){
        this.name = name;
        this.age = age;
    }

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
        return "Cat "  + name + " runs";
    }
}
