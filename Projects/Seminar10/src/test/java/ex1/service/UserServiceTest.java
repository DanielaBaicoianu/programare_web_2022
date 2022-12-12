package ex1.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ex1.dto.UserDto;
import ex1.entity.User;
import ex1.exception.UserNotFoundException;
import ex1.mapper.UserMapper;
import ex1.repository.UserRepository;
import ex1.utils.UsersMocks;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    User user;

    UserDto userDto;

    @Test
    public void testAddUser() {
        //GIVEN
        user = UsersMocks.mockUser();
        userDto = UsersMocks.mockUserDto();

        //WHEN
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        when(userMapper.mapToUser(userDto)).thenReturn(user);

        UserDto result = userService.addUser(userDto);

        //THEN
        assertTrue(result.getUsername().equals(userDto.getUsername()));
        assertThat(result.getCnp()).isNotNull();
        assertNotNull(result);
        verifyNoMoreInteractions(userRepository, userMapper);
    }

    @Test
    public void testUpdateUserException() {
        //GIVEN
        user = null;
        userDto = null;

        //WHEN
        when(userRepository.getReferenceById(1L)).thenReturn(user);

        //THEN
        UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, "username"));
        assertEquals(String.format("No user with id %s found", 1L), userNotFoundException.getMessage());
    }

    @Test
    public void testUpdateUser() {
        //GIVEN
        user = UsersMocks.mockUser();
        userDto = UsersMocks.mockUserDto();
        userDto.setUsername("test");
        //WHEN
        when(userRepository.getReferenceById(1L)).thenReturn(user);
        User user2 = UsersMocks.mockUser();
        user2.setUsername("test");
        when(userMapper.mapToUserDto(user2)).thenReturn(userDto);
        when(userRepository.save(user)).thenReturn(user2);

        //THEN
        UserDto result = userService.updateUser(1L, "test");
        assertThat(result).isNotNull();
        assertTrue(result.getUsername().equals("test"));
    }

    @Test
    public void testAddUserWithTransactional() {

        //GIVEN
        user = UsersMocks.mockUser();
        userDto = UsersMocks.mockUserDto();
        Boolean shouldThrowException = true;

        //WHEN
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapToUser(userDto)).thenReturn(user);

        //THEN
        UserNotFoundException userNotFoundException =
                assertThrows(UserNotFoundException.class, () -> userService.addUserWithTransactional(userDto, shouldThrowException));

        assertEquals("Exception after transactional", userNotFoundException.getMessage());

    }
}
