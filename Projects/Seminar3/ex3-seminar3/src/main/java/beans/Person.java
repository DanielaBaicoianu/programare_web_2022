package beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static constants.Constants.*;

@Component
//Eager class => created by default by spring at the startup of the app context
public class Person {

    @Lazy(value = true)
    @Autowired
    private Pet pet;

    @PostConstruct
    private void postConstructMethod(){
        System.out.println(POST_CONSTRUCT_MESSAGE + this.getClass().getName());
    }


    @PreDestroy
    private void preDestroyMethod(){
        System.out.println(PRE_DESTROY_MESSAGE);
    }

}
