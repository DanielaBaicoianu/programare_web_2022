package ex1.service.impl;

import static ex1.constants.Constants.NO_USER_FOUND;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ex1.dto.UserDto;
import ex1.entity.User;
import ex1.exception.UserNotFoundException;
import ex1.mapper.UserMapper;
import ex1.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    public UserDto addUser(UserDto userDto) {
        return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userDto)));
    }

    public List<UserDto> getByUsername(String username) {
        List<UserDto> userDtos = userRepository.findByUsername(username).stream()
                .map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
        if (userDtos.isEmpty()) {
            throw new UserNotFoundException(String.format("No user %s found on the db", username));
        }
        return userDtos;
    }

    public List<UserDto> getByUsernameAndType(String username, String type) {
        List<UserDto> userDtos = userRepository.filterByType(username, type).stream()
                .map(user -> userMapper.mapToUserDto(user)).collect(Collectors.toList());
        if (userDtos.isEmpty()) {
            throw new UserNotFoundException(String.format(NO_USER_FOUND, username));
        }
        return userDtos;

    }

    public UserDto findFirstContaining(String fullName) {
        User user = userRepository.findFirstByFullNameContaining(fullName);
        if (user == null) {
            throw new UserNotFoundException(String.format("No user containing %s in full name found on the db", fullName));
        }
        return userMapper.mapToUserDto(user);
    }

    public UserDto updateUsername(Long id, String username) {

        User user = userRepository.getReferenceById(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("No user with id %s found", id));
        }
        user.setUsername(username);
        return userMapper.mapToUserDto(userRepository.save(user));
    }


    @Transactional
    public void addUserWithTransactional(UserDto userDto, Boolean shouldThrowException){
        userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userDto)));

        if(shouldThrowException){
            throw new UserNotFoundException("After transactional");
        }

        userDto.setUsername(userDto.getUsername() + Math.random());
        userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userDto)));

    }
}
