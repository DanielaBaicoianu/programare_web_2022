package ex1.mapper;

import org.springframework.stereotype.Component;

import ex1.dto.UserDTO;
import ex1.entity.User;

@Component
public class UserMapper {

    public User mapToUser(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .information(userDTO.getAccountInformation())
                .build();
    }

    public UserDTO mapToDto(User user){
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .accountInformation(user.getInformation())
                .build();
    }
}
