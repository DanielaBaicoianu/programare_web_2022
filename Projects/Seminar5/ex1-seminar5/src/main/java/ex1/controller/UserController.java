package ex1.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ex1.dto.UserDTO;
import ex1.mapper.UserMapper;
import ex1.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUsers")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody(required = true) UserDTO userDTO, HttpServletResponse response) {
        if (!userService.findUserByUsername(userDTO.getUsername()).isPresent()) {
            userService.addUser(userDTO);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return String.format("User %s added", userDTO.getUsername());
        } else {
            response.setStatus(HttpServletResponse.SC_FOUND);
            return String.format("User %s already present.", userDTO.getUsername());
        }
    }


    @PostMapping("/addUser/{username}")
    public UserDTO addUser(@PathVariable(value = "username", required = true) String username,
                           @RequestParam(defaultValue = "anonimus@test.ro") String email,
                           @RequestParam(required = false, defaultValue = "no info") String information) {
        userService.addUser(UserDTO.builder()
                .username(username)
                .email(email)
                .accountInformation(information)
                .build());
        return userMapper.mapToDto(userService.findUserByUsername(username).get());
    }

    @DeleteMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable String username, HttpServletResponse response) {
        boolean userDeleted = userService.deleteUser(username);
        if (userDeleted) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return String.format("User %s was deleted.", username);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return String.format("User %s not found.", username);
        }
    }
}
