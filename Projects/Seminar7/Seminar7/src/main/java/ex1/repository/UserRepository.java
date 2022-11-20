package ex1.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import ex1.entity.User;

@Repository
public class UserRepository {

    private List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    public void delete(User user) {
        userList.remove(user);
    }

    public User addUser(User user) {
        userList.add(user);
        return user;
    }

    public User edit(User oldUser, User newUser) {
        userList.remove(oldUser);
        userList.add(newUser);
        return newUser;
    }

    @PostConstruct
    private void initializeUsers() {
        userList.add(User.builder()
                .firstname("Andreea")
                .lastname("Popescu")
                .email("andreeapopescu@email.com")
                .information("Information about Andreea")
                .build());
        userList.add(User.builder()
                .firstname("Cristian")
                .lastname("Ionescu")
                .email("cristian.ionescu@email.com")
                .information("Information about Cristian")
                .build());
    }
}
