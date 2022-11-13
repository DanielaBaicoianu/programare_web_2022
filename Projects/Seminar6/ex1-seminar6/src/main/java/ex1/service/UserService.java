package ex1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ex1.dto.UserDTO;
import ex1.entity.User;
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
        List<UserDTO> userDTOS = userRepository.getUsers().stream().map(u -> userMapper.mapToDTO(u)).collect(Collectors.toList());
        if (userDTOS.size() > 0) {
            return userDTOS;
        } else {
            throw new UserNotFoundException("User list is empty");
        }
    }

    public UserDTO addUser(UserDTO userDTO) {
        if (userRepository.getUsers().stream().filter(u -> u.getUsername().equals(userDTO.getName())).findFirst().isEmpty()) {
            userRepository.add(userMapper.mapToUser(userDTO));
        }
        return userMapper.mapToDTO(userRepository.getUsers().stream().filter(u -> u.getUsername().equals(userDTO.getName())).findFirst().orElseThrow(() -> new UserNotFoundException("User not found. User was not added to the list")));
    }

    public boolean deleteUser(String username) {
        User user = userRepository.getUsers().stream().filter(u -> u.getUsername().equals(username)).findFirst().orElseThrow(() -> new UserNotFoundException("User not found, can`t delete user"));
        userRepository.delete(user);
        return true;
    }

    public UserDTO editUser(UserDTO userDTO, String username) {
        User user = userRepository.getUsers().stream().filter(u -> u.getUsername().equals(username)).findFirst().orElseThrow(() -> new UserNotFoundException("User not found, can`t update user"));

        return userMapper.mapToDTO(userRepository.editUser(userMapper.mapToUser(userDTO), user));
    }

    public UserDTO editUserPartial(UserDTO userDTO, String username) {
        User user = userRepository.getUsers().stream().filter(u -> u.getUsername().equals(username)).findFirst().orElseThrow(() -> new UserNotFoundException("User not found, can`t update user"));
        userDTO.setName(userDTO.getName() != null ? userDTO.getName() : user.getUsername());
        userDTO.setEmail(userDTO.getEmail() != null ? userDTO.getEmail() : user.getEmail());
        userDTO.setInfo(userDTO.getInfo() != null ? userDTO.getInfo() : user.getAdditionalInfo());
        return userMapper.mapToDTO(userRepository.editUser(userMapper.mapToUser(userDTO), user));

    }
}
