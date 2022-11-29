package ex1.mapper;

import org.springframework.stereotype.Component;

import ex1.dto.UserDto;
import ex1.entity.User;
import ex1.entity.UserDetails;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .userType(userDto.getUserType())
                .fullName(userDto.getFullName())
                .userDetails(UserDetails
                        .builder()
                        .id(userDto.getId())
                        .age(userDto.getAge())
                        .cnp(userDto.getCnp())
                        .otherInformation(userDto.getOtherInformation())
                        .build())
                .build();
    }

    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .userType(user.getUserType())
                .fullName(user.getFullName())
                .age(user.getUserDetails().getAge())
                .cnp(user.getUserDetails().getCnp())
                .otherInformation(user.getUserDetails().getOtherInformation())
                .build();
    }


}
