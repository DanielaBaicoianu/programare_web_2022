package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import main.entity.User;

@Service
public class UserService {

   private List<User> userList = new ArrayList<>();


    public List<User> getUserList() {
        return userList;
    }

    public void addUser(User user){
       Optional<User> userFound = userList.stream()
               .filter(u-> u.getUsername().equals(user.getUsername()))
               .findFirst();
       if(!userFound.isPresent())
           userList.add(user);
   }

   public Optional<User> findByUserName(String username){
        return userList.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
   }

   public void deleteUser(String username){
        Optional<User> user = findByUserName(username);
        if(user.isPresent())
            userList.remove(user.get());
   }

   public String toString(){
        String users = "";
        for(User user : userList)
            return users += user.toString() + "\n";
        return users;
   }

}
