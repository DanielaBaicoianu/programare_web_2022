import beans.Car;
import beans.Person;
import configuration.ProjectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

		Person person1 = context.getBean(Person.class);
		Person person2 = context.getBean(Person.class);

		System.out.println("Person: ");

		if (person2.equals(person1))
			System.out.println("Singleton");
		else
			System.out.println("Prototype");

		person1.setName("Ana");

		System.out.println("After setting person 1 name: ");
		if (person2.getName().equals(person1.getName()))
			System.out.println("Singleton");
		else
			System.out.println("Prototype");


		Car car1 = context.getBean(Car.class);
		Car car2 = context.getBean(Car.class);

		System.out.println("Car:");

		if (car2.equals(car1))
			System.out.println("Singleton");
		else
			System.out.println("Prototype");

		car1.setModel("Ford");
		System.out.println("After setting car 1 model: ");

		try {
			car2.getModel().equals(car1.getModel());
			System.out.println("Singleton");
		} catch (NullPointerException ex) {
			System.out.println("Prototype");
		}
	}

}
