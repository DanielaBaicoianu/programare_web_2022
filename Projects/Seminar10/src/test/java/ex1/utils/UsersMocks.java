package ex1.utils;

import ex1.dto.UserDto;
import ex1.entity.User;
import ex1.entity.UserDetails;

public class UsersMocks {

    public static User mockUser() {
        return User.builder()
                .id(1L)
                .username("userttest")
                .fullName("Cristian Ionescu")
                .userType("secondary")
                .userDetails(UserDetails.builder()
                        .cnp("564454")
                        .age(20)
                        .otherInformation("info")
                        .build())
                .build();
    }

    public static UserDto mockUserDto() {
        return UserDto.builder()
                .username("userttest")
                .fullName("Cristian Ionescu")
                .userType("secondary")
                .cnp("564454")
                .age(20)
                .otherInformation("info")
                .build();
    }

}
