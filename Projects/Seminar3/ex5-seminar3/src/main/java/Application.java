import config.ProjectConfig;
import model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.UserService;

public class Application {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.addUser("Andrei", "20");

        userService.logUser();


    }

}

