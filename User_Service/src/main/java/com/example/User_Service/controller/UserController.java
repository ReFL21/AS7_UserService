package com.example.User_Service.controller;

import com.example.User_Service.business.ICreateUser;
import com.example.User_Service.business.IDeleteUser;
import com.example.User_Service.business.IGetAllUsers;
import com.example.User_Service.domain.CreateUserRequest;
import com.example.User_Service.domain.CreateUserResponse;
import com.example.User_Service.domain.GetAllUsersResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = {"http://localhost:8083"})
@RequestMapping("/users")
@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    @Autowired
    private IGetAllUsers iGetAllUsers;
//
//    @Autowired
//    private GetUserUseCase getUserUseCase;
//
    @Autowired
    private ICreateUser createUser;
//
    @Autowired
    private IDeleteUser deleteUser;
//
//    @Autowired
//    private LoginUseCase loginUseCase;
//
//    @IsAuthenticated
//    @RolesAllowed({"Admin"})
    @GetMapping

    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        GetAllUsersResponse response = iGetAllUsers.getAllUsers();

        return ResponseEntity.ok(response);
    }
//    @IsAuthenticated
//    @RolesAllowed({"Customer"})
//    @GetMapping("{id}")
//    public ResponseEntity<User> getUser(@PathVariable(value = "id") final Long id) {
//        final Optional<User> userOptional = getUserUseCase.getUser(id);
//        if (userOptional.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(userOptional.get());
//    }
//
//
    @PostMapping("/register")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest userRequest){
        CreateUserResponse response = createUser.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
//    @IsAuthenticated
//    @RolesAllowed({"Admin"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        deleteUser.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
//        LoginResponse response = loginUseCase.login(request);
//        return ResponseEntity.ok(response);
    }

