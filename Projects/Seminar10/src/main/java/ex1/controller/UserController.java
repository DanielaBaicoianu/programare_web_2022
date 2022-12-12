package ex1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ex1.dto.UserDto;
import ex1.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @PostMapping("/{shouldReturn}")
    public ResponseEntity<List<UserDto>> addUsers(@RequestBody UserDto userDto, @PathVariable Boolean shouldReturn){
        userService.addUserWithTransactional(userDto, shouldReturn);
        return ResponseEntity.ok(userService.getUserByUsername(userDto.getUsername()));
    }

    @GetMapping("/{fullName}")
    public ResponseEntity<UserDto> getUserFullNameContainingString(@PathVariable String fullName){
        return ResponseEntity.ok(userService.getUserFullContains(fullName));
    }

    @GetMapping("/{username}/{userType}")
    public ResponseEntity<List<UserDto>> getUsersByUsernameAndType(@PathVariable String username, @PathVariable String userType){
        return ResponseEntity.ok(userService.getAllByUsernameAndType(username, userType));
    }

    @PutMapping("/update/{id}/{username}")
    public ResponseEntity<UserDto> updateUsername(@PathVariable String username, @PathVariable Long id){
        return ResponseEntity.ok(userService.updateUser(id, username));
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<List<UserDto>> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

}
