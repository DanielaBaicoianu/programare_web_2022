package ex1.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ex1.dto.PartialUserDTO;
import ex1.dto.UserDTO;
import ex1.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUser() {
        List<UserDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO user = userService.addUser(userDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{name:[a-zA-Z ]*}")
    public ResponseEntity<String> deleteUser(@PathVariable String name) {
        userService.deleteFirst(name);
        return ResponseEntity.ok("User Deleted");
    }

    @DeleteMapping("all/{name:[a-zA-Z ]*}")
    public ResponseEntity<String> deleteUsers(@PathVariable String name) {
        int users = userService.deleteAll(name);
        return ResponseEntity.ok(String.format("%s users Deleted", users));
    }

    @PatchMapping("/{name:[a-zA-Z ]*}")
    public ResponseEntity<UserDTO> updateUserName(@PathVariable String name, @Valid @RequestBody PartialUserDTO partialUser) {
        UserDTO user = userService.updateUsername(name, partialUser);
        return ResponseEntity.ok(user);
    }
}
