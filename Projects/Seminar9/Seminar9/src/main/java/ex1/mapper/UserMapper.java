package ex1.mapper;

import org.springframework.stereotype.Component;

import ex1.dto.UserDto;
import ex1.entity.User;

@Component
public class UserMapper {


    public User mapToUser(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .fullName(userDto.getFullName())
                .userType(userDto.getUserType())
                .build();
    }

    public UserDto mapToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .userType(user.getUserType())
                .build();
    }

}
