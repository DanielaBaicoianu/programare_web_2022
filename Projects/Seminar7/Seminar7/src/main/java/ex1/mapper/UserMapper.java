package ex1.mapper;

import org.springframework.stereotype.Component;

import ex1.dto.UserDTO;
import ex1.entity.User;

@Component
public class UserMapper {

    public User mapUserDroToUser(UserDTO userDTO){
        return User.builder()
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .email(userDTO.getEmail())
                .information(userDTO.getInfo())
                .build();
    }

    public UserDTO mapUserToUserDto(User user){
        return UserDTO.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .info(user.getInformation())
                .build();
    }

}
