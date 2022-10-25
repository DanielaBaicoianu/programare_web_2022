package beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static constants.Constants.*;

@Component
public class Person {

    @PostConstruct
    private void postConstructMethod(){
        System.out.println(POST_CONSTRUCT_MESSAGE + this.getClass().getName());
    }


    @PreDestroy
    private void preDestroyMethod(){
        System.out.println(PRE_DESTROY_MESSAGE);
    }

}
