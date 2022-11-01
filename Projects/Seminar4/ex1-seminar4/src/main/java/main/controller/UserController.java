package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.entity.User;
import main.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        userService.addUser(User.builder().username("Andreea").email("test1@test").password("12345").build());
        userService.addUser(User.builder().username("Ioana").email("test1@test").password("456446").build());
    }

    @GetMapping("/getUsers/{name}")
    public String getUsers(@PathVariable String name){

        return userService.findByUserName(name).toString();
    }

    @GetMapping("/getUsers")
    public String getUsers(){
        return userService.getUserList().toString();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteUser(@PathVariable String name){
        userService.deleteUser(name);
    }
}
