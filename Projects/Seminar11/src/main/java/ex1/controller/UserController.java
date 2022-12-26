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
import ex1.service.impl.UserService;
import ex1.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userServiceImpl.addUser(userDto));
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<UserDto>> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userServiceImpl.getByUsername(username));
    }

    @GetMapping("/{username}/{type}")
    public ResponseEntity<List<UserDto>> getUserByUsernameAndType(@PathVariable String username, @PathVariable String type){
        return ResponseEntity.ok(userServiceImpl.getByUsernameAndType(username,type));
    }

    @GetMapping("/containing/{fullName}")
    public ResponseEntity<UserDto> getUserByFullNameContaining(@PathVariable String fullName){
        return ResponseEntity.ok(userServiceImpl.findFirstContaining(fullName));
    }

    @PutMapping("/{id}/{username}")
    public ResponseEntity<UserDto> updateUsername(@PathVariable Long id, @PathVariable String username){
        return ResponseEntity.ok(userServiceImpl.updateUsername(id, username));
    }

    @PostMapping("/{shouldThrow}")
    public ResponseEntity<List<UserDto>> addUserWithTransactional(@RequestBody UserDto userDto, @PathVariable Boolean shouldThrow){
        userServiceImpl.addUserWithTransactional(userDto, shouldThrow);
        return ResponseEntity.ok(userServiceImpl.getByUsername(userDto.getUsername()));
    }

}
