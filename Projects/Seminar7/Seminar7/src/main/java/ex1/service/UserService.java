package ex1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ex1.dto.PartialUserDTO;
import ex1.dto.UserDTO;
import ex1.entity.User;
import ex1.exception.DataNotFoundException;
import ex1.exception.UserNotFoundException;
import ex1.mapper.UserMapper;
import ex1.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDTO> getUsers() {
        List<UserDTO> users = userRepository.getUserList()
                .stream().map(user -> userMapper.mapUserToUserDto(user))
                .collect(Collectors.toList());
        if (users.isEmpty()) {
            throw new DataNotFoundException("No users found.");
        }
        return users;
    }

    public UserDTO addUser(UserDTO userDTO) {
        return userMapper.mapUserToUserDto(userRepository.addUser(userMapper.mapUserDroToUser(userDTO)));
    }

    public boolean deleteFirst(String name) {
        Optional<User> firstUser = userRepository.getUserList().stream()
                .filter(u -> u.getFirstname().equals(name))
                .findFirst();
        if (firstUser.isEmpty()) {
            throw new UserNotFoundException(String.format("Can`t delete user %s. Not found", name));
        }
        userRepository.delete(firstUser.get());
        return true;
    }

    public int deleteAll(String name) {
        List<User> users = userRepository.getUserList().stream()
                .filter(u -> u.getFirstname().equals(name))
                .collect(Collectors.toList());
        if (users.isEmpty()) {
            throw new DataNotFoundException(String.format("No users with name %s found.", name));
        }

        for (User user : users) {
            userRepository.delete(user);
        }
        return users.size();
    }

    public UserDTO updateUser(String name, UserDTO userDTO) {
        User user = userRepository.getUserList().stream()
                .filter(u -> u.getFirstname().equals(name))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found. Can`t update user %s.", name)));
        return userMapper.mapUserToUserDto(userRepository.edit(user, userMapper.mapUserDroToUser(userDTO)));
    }

    public UserDTO updateUsername(String name, PartialUserDTO partialUserDTO){
        User user = userRepository.getUserList().stream()
                .filter(u -> u.getFirstname().equals(name))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found. Can`t update user %s.", name)));
        User newUser = User.builder()
                .firstname(partialUserDTO.getFirstname())
                .lastname(partialUserDTO.getLastname())
                .email(user.getEmail())
                .information(user.getInformation())
                .build();
        return userMapper.mapUserToUserDto(userRepository.edit(user, newUser));
    }


}
