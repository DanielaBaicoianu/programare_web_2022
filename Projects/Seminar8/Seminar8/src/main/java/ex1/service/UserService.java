package ex1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ex1.dto.UserDto;
import ex1.entity.User;
import ex1.mapper.UserMapper;
import ex1.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User getUser(String username){
        return userRepository.getUserDetails(username);
    }

    public User addUser(UserDto userDto){
       return  userRepository.save(userMapper.mapToUser(userDto));
    }

    public List<UserDto> getAllUsers(){
        return userRepository.getAllUsersWithDetails();
    }


}
