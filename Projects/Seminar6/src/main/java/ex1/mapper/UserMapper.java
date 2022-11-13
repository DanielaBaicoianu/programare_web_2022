package ex1.mapper;

import org.springframework.stereotype.Component;

import ex1.dto.UserDTO;
import ex1.entity.User;

@Component
public class UserMapper {

    public UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .name(user.getUsername())
                .email(user.getEmail())
                .info(user.getAdditionalInfo())
                .build();
    }

    public User mapToUser(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getName())
                .email(userDTO.getEmail())
                .additionalInfo(userDTO.getInfo())
                .build();
    }

}
