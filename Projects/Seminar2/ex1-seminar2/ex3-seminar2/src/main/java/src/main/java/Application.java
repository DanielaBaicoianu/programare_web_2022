package src.main.java;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import src.main.java.configuration.ProjectConfiguration;
import src.main.java.model.Person;
import src.main.java.model.Pet;


public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

		Pet pet = context.getBean("bird", Pet.class);

		Person person1 = context.getBean("person" , Person.class);
		System.out.println(person1.toString());
		System.out.println(person1.getPet().walk());

		Person person2 = context.getBean(Person.class);
		System.out.println(person2.toString());
		System.out.println(person2.getPet().walk());
	}
}
