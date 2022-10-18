package src.main.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import src.main.java.configuration.ProjectConfiguration;
import src.main.java.model.Person;
import src.main.java.model.Pet;


public class Ex1Seminar1Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

		Person person = context.getBean("person1", Person.class);
		System.out.println(person.getName());

		Pet pet = context.getBean("pet1", Pet.class);
		System.out.println(pet.getName() + ", age: " + pet.getAge());

		Person person2 = context.getBean("person2", Person.class);
		System.out.println(person2.getName());

	}

}
