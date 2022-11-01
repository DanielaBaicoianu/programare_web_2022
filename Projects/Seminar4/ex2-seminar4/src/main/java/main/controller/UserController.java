package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.entity.User;
import main.service.UserService;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        userService.addUser(User.builder().username("Alina").email("alinatest@test1.rui").password("1234").build());
    }

    @GetMapping("/view-create")
    public String createUser(User user){
        return "createuser";
    }

    @PostMapping("/create")
    public String viewUsers(User user, Model model){
        userService.addUser(user);
        model.addAttribute("users", userService.getUsers());
        return "viewusers";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username, Model model){
        userService.deleteUser(username);
        model.addAttribute("users", userService.getUsers());
        return "viewusers";
    }
}
