package service;

import aspects.MarkedForLogging;
import model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<User> users = new ArrayList<>();

    public void addUser(String name, String age){
        User user = User.builder()
                .name(name)
                .age(age)
                .build();
        System.out.println(String.format("User %s with age %s was added",name, age));

        users.add(user);
    }

    @MarkedForLogging
    public void logUser(){
        System.out.println("Users " + users.toString());
    }


}
