package ex1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import ex1.dto.UserDto;
import ex1.exception.UserNotFoundException;
import ex1.service.impl.UserServiceImpl;
import ex1.utils.UserMocks;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @Test
    public void testAddUser() throws Exception {
        UserDto userDto = UserMocks.mockUserDto();
        when(userService.addUser(any())).thenReturn(userDto);
        String userDtoBody = mapper.writeValueAsString(userDto);
        MvcResult result = mockMvc.perform(post("/users/add")
                        .content(userDtoBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.fullName").value("Test User"))
                .andExpect(jsonPath("$.userType").value("test"))
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), userDtoBody);
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        UserDto userDto = UserMocks.mockUserDto();
        when(userService.getByUsername("testuser")).thenReturn(Arrays.asList(userDto));
        MvcResult result = mockMvc.perform(get("/users/{username}", "testuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].fullName").value(userDto.getFullName()))
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(Arrays.asList(userDto)));
    }

    @Test
    public void testGetUserByUsernameWithException() throws Exception {
        when(userService.getByUsername("test")).thenThrow(UserNotFoundException.class);
        mockMvc.perform(get("/users/{username}", "test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    public void testGetUserByUsernameAndType() throws Exception {
        UserDto userDto = UserMocks.mockUserDto();
        when(userService.getByUsernameAndType("testuser", "test")).thenReturn(Arrays.asList(userDto));
        MvcResult result = mockMvc.perform(get("/users/{username}/{type}", "testuser", "test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(Arrays.asList(userDto)));
    }

    @Test
    public void testGetUserByUsernameAndTypeWithException() throws Exception {
        when(userService.getByUsernameAndType(any(), any())).thenThrow(UserNotFoundException.class);
        MvcResult result = mockMvc.perform(get("/users/{username}/{type}", "user1", "admin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());

    }

    @Test
    public void updateUsername() throws Exception {
        UserDto userDto = UserMocks.mockUserDto();
        when(userService.updateUsername(any(), any())).thenReturn(userDto);
        mockMvc.perform(put("/users/{id}/{username}", 1L, "testuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    public void updateUsernameWithException() throws Exception {
        when(userService.updateUsername(any(), any())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(put("/users/{id}/{username}", 45L, "test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
