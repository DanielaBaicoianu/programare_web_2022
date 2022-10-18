package src.main.java.model;

public class Person {

    private String name;

    private Pet pet;

    public void setName(String name) {
        this.name = name;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
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
