import beans.Person;
import beans.Pet;
import configuration.ProjectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
		//context.getBean(Person.class); //the PostConstruct method will be called fot the Person bean,
												//because it is an eager bean and it`s created at startup of
												//app context

		//context.getBean(Pet.class); //the PostConstruct method will not be called fot the Cat bean,
										//because it is a @Lazy bean and was not requested yet

		context.close(); //the Predestroy method will be called fot the Person bean,
						//because it is an eager bean and it`s created at startup of
						//app context
	}

}
