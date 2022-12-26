package ex1.utils;

import ex1.dto.UserDto;
import ex1.entity.User;
import ex1.entity.UserDetails;

public class UserMocks {

    public static User mockUser(){
        return User.builder()
                .id(1L)
                .username("testuser")
                .fullName("Test User")
                .userType("test")
                .userDetails(UserDetails.builder()
                        .cnp("1234")
                        .age(20)
                        .otherInformation("test information")
                        .build())
                .build();
    }

    public static UserDto mockUserDto(){
        return UserDto.builder()
                .id(1L)
                .username("testuser")
                .fullName("Test User")
                .userType("test")
                .cnp("1234")
                .age(20)
                .otherInformation("test information")
                .build();
    }
}
