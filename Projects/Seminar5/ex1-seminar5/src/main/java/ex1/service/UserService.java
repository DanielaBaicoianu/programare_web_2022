package ex1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ex1.dto.UserDTO;
import ex1.entity.User;
import ex1.mapper.UserMapper;
import ex1.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public Optional<User> findUserByUsername(String username) {
        Optional<User> userFound = userRepository.getAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
        return userFound;
    }

    public List<UserDTO> getAllUsers(){
        List<UserDTO> userDTOList = userRepository.getAll()
                .stream()
                .map(user -> userMapper.mapToDto(user))
                .collect(Collectors.toList());
        return userDTOList;
    }

    public void addUser(UserDTO userDTO) {
        if (findUserByUsername(userDTO.getUsername()).isEmpty()) {
            userRepository.add(userMapper.mapToUser(userDTO));
        }
    }

    public boolean deleteUser(String username) {
        Optional<User> userFound = findUserByUsername(username);
        if (userFound.isPresent()) {
            userRepository.delete(userFound.get());
            return true;
        } else {
            return false;
        }
    }


}
