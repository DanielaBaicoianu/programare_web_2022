package ex1.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import ex1.dto.UserDto;
import ex1.entity.User;
import ex1.utils.UsersMocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class MapperTest {

    @InjectMocks
    UserMapper userMapper;

    @Test
    public void testMapUserDtoToUser() {
        //GIVEN
        UserDto userDto = UsersMocks.mockUserDto();

        //WHEN
        User user1 = userMapper.mapToUser(userDto);

        //THEN
        assertTrue(user1.getUsername().equals("userttest"));
        assertThat(user1.getUserDetails().getAge().equals(20));
        assertNotNull(user1.getUserDetails());
    }

    @Test
    public void testMapUserToUserDto() {
        User user = UsersMocks.mockUser();

        UserDto userDto = userMapper.mapToUserDto(user);
        assertTrue(userDto.getUsername().equals("userttest"));
        assertNotNull(userDto.getId());
    }
}
