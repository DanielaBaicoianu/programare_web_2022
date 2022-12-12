package ex1.mapper;

import org.springframework.stereotype.Component;

import ex1.dto.UserDto;
import ex1.entity.User;
import ex1.entity.UserDetails;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto){
        return User.builder()
                .username(userDto.getUsername())
                .fullName(userDto.getFullName())
                .userType(userDto.getUserType())
                .userDetails(UserDetails.builder()
                        .cnp(userDto.getCnp())
                        .age(userDto.getAge())
                        .otherInformation(userDto.getOtherInformation())
                        .build())
                .build();
    }

    public UserDto mapToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .userType(user.getUserType())
                .otherInformation(user.getUserDetails().getOtherInformation())
                .cnp(user.getUserDetails().getCnp())
                .age(user.getUserDetails().getAge())
                .build();
    }


}
