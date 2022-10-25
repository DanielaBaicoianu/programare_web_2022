package aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectForUserService {

    @Before("execution(* service.*.*(..))")
    public void logBeforeCallingAnyMethodFromServicePackage(){
        System.out.println("A method from service package was called\n");
    }

    @Before("execution(* service.UserService.addUser(..)) && args(name, ..)")
    public void logUserNameBeforeCreatingUser(String name){
        System.out.println(String.format("User %s will be created.\n", name));
    }

    @After("execution(* service.UserService.addUser(..))")
    public void logAfterCallingAnyMethodFromServicePackage(){
        System.out.println("Create user flow finished successfully\n");
    }

    @Around("@annotation(MarkedForLogging)")
    public void logMethodsAnnotatedWithMarkedForLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        System.out.println("Method annotated with MarkedForLogging annotation was called\n");

    }

    @Around("execution(* service.UserService.addUser(..))")
    public void changeUserName(ProceedingJoinPoint joinPoint) throws Throwable {
        Object [] newArgs = {"Ioana", "14"};
        joinPoint.proceed(newArgs);
    }
}
