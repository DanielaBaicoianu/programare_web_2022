package ex1.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ex1.entity.User;

@Repository //indicates that the class provides the methods to get, update, delete, storage objects (in our case users)
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public UserRepository(){
        initializeUsers();
    }

    public List<User> getAll() {
        return users;
    }

    public void delete(User user) {
        users.remove(user);
    }

    public void add(User user) {
        users.add(user);
    }

    public void initializeUsers() {
        users.add(User.builder()
                .username("ana.popescu")
                .email("ana.popescu@test.ro")
                .information("This is Ana`s account")
                .build());
        users.add(User.builder()
                .username("maria.ionescu")
                .email("maria.ionescu@test.ro")
                .information("This is Maria`s account")
                .build());

    }


}
