package beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static constants.Constants.CAT_TALK;
import static constants.Constants.POST_CONSTRUCT_MESSAGE;

@Lazy //the class in created only when it`s requested
@Component
public class Cat implements Pet{

    @Value("${cat.name}")
    String name;

    @PostConstruct
    private void catWasInitialised(){
        System.out.println(POST_CONSTRUCT_MESSAGE +  this.getClass().getName());
    }

    @Override
    public String talk() {
        return CAT_TALK;
    }

    @Override
    public String toString() {
        return "Cat " + name;
    }
}
