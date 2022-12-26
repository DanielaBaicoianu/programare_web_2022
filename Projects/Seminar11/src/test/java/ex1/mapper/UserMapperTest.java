package ex1.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import ex1.dto.UserDto;
import ex1.entity.User;
import ex1.utils.UserMocks;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    @InjectMocks
    UserMapper userMapper;

    @Test
    public void mapToUserTest(){
        //GIVEN
        UserDto userDto = UserMocks.mockUserDto();

        //WHEN
        User user1 = userMapper.mapToUser(userDto);

        //THEN
        assertTrue(user1.getUsername().equals(userDto.getUsername()));
        assertTrue(user1.getUserDetails().getCnp().equals(userDto.getCnp()));
        assertNotNull(user1.getUserDetails());
    }


    @Test
    public void mapToUserDtoTest(){
        //GIVEN
        User user = UserMocks.mockUser();

        //WHEN
        UserDto userDto = userMapper.mapToUserDto(user);

        //THEN
        assertTrue(userDto.getUsername().equals(user.getUsername()));
        assertTrue(userDto.getCnp().equals(user.getUserDetails().getCnp()));
        assertNotNull(userDto.getUserType());
    }



}
