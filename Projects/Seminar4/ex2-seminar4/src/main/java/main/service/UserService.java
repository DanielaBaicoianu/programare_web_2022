package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import main.entity.User;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();


    public void addUser(User user){
       Optional<User> userFound = users.stream().filter(u -> u.getUsername().equals(user.getUsername()))
               .filter(u -> u.getEmail().equals(user.getEmail()))
               .findFirst();
       if(!userFound.isPresent())
           users.add(user);
    }

    public Optional<User> findUserByUserName(String username){
            return users.stream().filter(u -> u.getUsername().equals(username))
                    .findFirst();
    }

    public void deleteUser(String userName){
        Optional<User> userFound = findUserByUserName(userName);
        if(userFound.isPresent())
            users.remove(userFound.get());
    }

    public void updateUser(String username){
        Optional<User> userFound = findUserByUserName(username);
        if(userFound.isPresent()){
            User user = User.builder().username(username).email(userFound.get().getEmail()).build();
            users.remove(userFound.get());
            users.add(user);
        }
    }

    public void createAndSave(String userName, String password, String email){
        User user = User.builder()
                .username(userName)
                .password(password)
                .email(email)
                .build();
        users.add(user);
    }

    public List<User> getUsers(){
        return users;
    }
}
