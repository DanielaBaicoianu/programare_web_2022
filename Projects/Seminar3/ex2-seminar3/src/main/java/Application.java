import beans.Person;
import beans.Pet;
import configuration.ProjectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
		context.getBean(Person.class);
		context.getBean(Pet.class);
		context.close();
	}

}
