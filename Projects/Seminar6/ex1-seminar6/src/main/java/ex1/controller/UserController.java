package ex1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ex1.dto.UserDTO;
import ex1.exception.UserNotFoundException;
import ex1.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDTO>> getUsers() {
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(userService.addUser(userDTO));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> delete(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok(String.format("User deleted %s", username));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("User %s is not present in the list", username));
        }
    }

    @PutMapping("/edit/{username}")
    public ResponseEntity<UserDTO> editFullUser(@Valid @RequestBody UserDTO userDTO, @PathVariable String username) {
        try {
            UserDTO user = userService.editUser(userDTO, username);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/editPartial/{username}")
    public ResponseEntity<UserDTO> editPartialUser(@RequestBody UserDTO userDTO, @PathVariable String username) {
        try {
            UserDTO user = userService.editUserPartial(userDTO, username);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
