package ex1.service;

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
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDto addUser(UserDto user) {
        return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(user)));
    }

    @Transactional
    public void addUserWithTransactional(UserDto user, Boolean shouldThrowException) {
        userRepository.save(userMapper.mapToUser(user));
        if (shouldThrowException) {
            throw new UserNotFoundException("Exception after transactional");
        }
        user.setUsername("maria" + Math.random());
        user.setFullName("Maria Ionescu");
        user.setCnp("789555");
        userRepository.save(userMapper.mapToUser(user));
    }

    public UserDto getUserFullContains(String fullName) {
        User user = userRepository.findFirstByFullNameContains(fullName);
        if (user == null) {
            throw new UserNotFoundException(String.format("No user with full name containing %s found", fullName));
        }
        return userMapper.mapToUserDto(user);
    }

    public List<UserDto> getAllByUsernameAndType(String username, String userType) {
        List<UserDto> users = userRepository.findUserByUsernameAndType(username, userType).stream()
                .map(u -> userMapper.mapToUserDto(u))
                .collect(Collectors.toList());
        if (users.isEmpty()) {
            throw new UserNotFoundException(String.format("No user with username %s or usertype %s found", username, userType));
        }
        return users;
    }

    public UserDto updateUser(Long id, String username) {
        User user = userRepository.getReferenceById(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("No user with id %s found", id));
        }
        user.setUsername(username);
        return userMapper.mapToUserDto(userRepository.save(user));
    }

    public List<UserDto> getUserByUsername(String username) {
        List<User> users = userRepository.findByUsername(username);
        if (users.isEmpty()) {
            throw new UserNotFoundException(String.format("No user with username %s found", username));
        }
        return users.stream()
                .map(user -> userMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }
}
