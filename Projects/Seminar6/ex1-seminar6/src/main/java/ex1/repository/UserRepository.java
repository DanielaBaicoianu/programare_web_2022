package ex1.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import ex1.entity.User;

@Repository
public class UserRepository {

    List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void add(User user) {
        users.add(user);
    }

    public void delete(User user) {
        users.remove(user);
    }

    public User editUser(User user, User userToBeEdited) {
        users.remove(userToBeEdited);
        users.add(user);
        return user;
    }

    @PostConstruct
    public void initializeUserList() {
        users.add(User.builder()
                .username("user1")
                .email("user1@email.com")
                .additionalInfo("User1 information")
                .build());
        users.add(User.builder()
                .username("user2")
                .email("user2@email.com")
                .additionalInfo("User2 information")
                .build());
    }
}
