package ex1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import ex1.service.impl.UserServiceImpl;
import ex1.utils.UserMocks;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    public void testAddUser(){
        //GIVEN
        User user = UserMocks.mockUser();
        UserDto userDto = UserMocks.mockUserDto();

        //WHEN
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        UserDto result = userServiceImpl.addUser(userDto);

        //THEN
        assertTrue(result.getCnp().equals(userDto.getCnp()));
        assertNotNull(result.getUserType());
        assertEquals(result.getAge(), userDto.getAge());
    }

    @Test
    public void testUpdateUserUsernameException(){
        //WHEN
        when(userRepository.getReferenceById(1L)).thenReturn(null);

        UserNotFoundException userNotFoundException =
                assertThrows(UserNotFoundException.class , () -> userServiceImpl.updateUsername(1L, "sth"));

        assertEquals("No user with id 1 found", userNotFoundException.getMessage());
    }


    @Test
    public void testUpdateUserUsernameSuccess(){
        //GIVEN
        User user = UserMocks.mockUser();
        UserDto userDto = UserMocks.mockUserDto();
        user.setUsername("newTestUsername");


        //WHEN
        when(userRepository.getReferenceById(1L)).thenReturn(user);
        userDto.setUsername("newTestUsername");
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        when(userRepository.save(user)).thenReturn(user);

        UserDto result = userServiceImpl.updateUsername(1L, "newTestUsername");

        //THEN
        assertEquals(result.getUsername(), userDto.getUsername());
        assertEquals(result.getUsername(), "newTestUsername");
    }

    @Test
    public void testAddUserWithTransactionalException(){
        //GIVEN
        User user = UserMocks.mockUser();
        UserDto userDto = UserMocks.mockUserDto();
        boolean shouldThrowException = true;

        //WHEN
        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        when(userRepository.save(user)).thenReturn(user);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userServiceImpl.addUserWithTransactional(userDto, shouldThrowException));

        //THEN
        assertEquals(exception.getMessage(), "After transactional");

    }

}
