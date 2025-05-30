package com.example.User_Service.controller;
import com.example.User_Service.business.*;
import com.example.User_Service.domain.*;
import com.example.User_Service.security.JwtUtil;
import com.example.User_Service.filter.JwtAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IGetAllUsers iGetAllUsers;

    @MockitoBean
    private IGetUserById getUserById;

    @MockitoBean
    private ICreateUser createUser;

    @MockitoBean
    private IDeleteUser deleteUser;

    @MockitoBean
    private ILoginUser loginUseCase;

    @MockitoBean
    private IUpdateUserInfo updateUserInfo;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private com.example.User_Service.filter.JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    @DisplayName("GET /users returns list of users")
    void testGetAllUsers() throws Exception {
        User user1 = new User(1L, "alice","asdasd", "alice@example.com","qweqwe","asdasd", "Customer");
        User user2 = new User(2L, "bob","asdasd", "bob@example.com","qweqwe","asdasd","Admin");
        List<User> userList= new ArrayList();
        userList.add(user1);
        userList.add(user2);
        GetAllUsersResponse response = GetAllUsersResponse.builder()
                .users(userList)
                .build();
        given(iGetAllUsers.getAllUsers()).willReturn(response);

        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users[0].id").value(1))
                .andExpect(jsonPath("$.users[0].name").value("alice"))
                .andExpect(jsonPath("$.users[1].id").value(2))
                .andExpect(jsonPath("$.users[1].name").value("bob"));
    }

    @Test
    @DisplayName("POST /users/register creates a new user")
    void testCreateUser() throws Exception {
        CreateUserRequest request = new CreateUserRequest("charlie","qweert", "charlie@example.com","qweqwe","asdasd", "password123");
        CreateUserResponse resp = CreateUserResponse.builder()
                .id(3L)

                .build();
        given(createUser.createUser(Mockito.any(CreateUserRequest.class))).willReturn(resp);

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3));

    }

    @Test
    @DisplayName("POST /users/register returns 400 Bad Request for invalid input")
    void testCreateUserInvalidInput() throws Exception {
        // Blank username and invalid email
        CreateUserRequest invalid = new CreateUserRequest("","", "","","", "");

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE /users/{id} deletes user")
    void testDeleteUser() throws Exception {
        doNothing().when(deleteUser).deleteUser(4L);

        mockMvc.perform(delete("/users/4"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("POST /users/login authenticates user")
    void testLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("qweqwe");
        loginRequest.setPassword("asdasd");
        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken("jwt-token")
                .build();
        given(loginUseCase.login(Mockito.any(LoginRequest.class))).willReturn(loginResponse);

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("jwt-token"));
    }

    @Test
    @DisplayName("POST /users/login returns 400 Bad Request for invalid login request")
    void testLoginInvalidInput() throws Exception {
        // Missing email and password
        LoginRequest invalid = new LoginRequest();

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /users/{id} returns user when found")
    void testGetUserByIdFound() throws Exception {
        User user = new User(5L, "dave", "dave@example.com", "Customer","qweert","asdqwe","asdad");
        given(getUserById.getUserById(5L)).willReturn(Optional.of(user));

        mockMvc.perform(get("/users/5").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("dave"));
    }

    @Test
    @DisplayName("GET /users/{id} returns 404 when not found")
    void testGetUserByIdNotFound() throws Exception {
        given(getUserById.getUserById(99L)).willReturn(Optional.empty());

        mockMvc.perform(get("/users/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /users/{id} with non-numeric ID returns 400 Bad Request")
    void testGetUserByIdInvalidId() throws Exception {
        mockMvc.perform(get("/users/abc").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /users/update updates user info")
    void testUpdateUserInfo() throws Exception {
        UpdateUserInfoRequest request = new UpdateUserInfoRequest(6L, "eve","qweqwew", "eve@example.com","asdasd","asdasd");
        UpdateUserInfoResponse resp = UpdateUserInfoResponse.builder()
                .id(6L)
                .username("qweqwew")
                .email("eve@example.com")
                .build();
        given(updateUserInfo.updateUserInfo(Mockito.any(UpdateUserInfoRequest.class))).willReturn(resp);

        mockMvc.perform(put("/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.username").value("qweqwew"));
    }

    @Test
    @DisplayName("PUT /users/update returns 400 Bad Request for invalid update data")
    void testUpdateUserInfoInvalidInput() throws Exception {
        // Missing required fields
        UpdateUserInfoRequest invalid = new UpdateUserInfoRequest(6L, "","", "","","");

        mockMvc.perform(put("/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }


}
