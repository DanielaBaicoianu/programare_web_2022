package src.main.java;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import src.main.java.configuration.ProjectConfiguration;
import src.main.java.model.Person;
import src.main.java.model.Pet;


public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

		Person person2 = context.getBean(Person.class);
		System.out.println(person2.toString());
		System.out.println(person2.getPet().walk());
	}
}
