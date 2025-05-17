package com.example.User_Service.controller;
import com.example.User_Service.business.*;
import com.example.User_Service.domain.*;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:5173"})
@RequestMapping("/users")
@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    @Autowired
    private IGetAllUsers iGetAllUsers;

    @Autowired
    private IGetUserById getUserById;

    @Autowired
    private ICreateUser createUser;
    //
    @Autowired
    private IDeleteUser deleteUser;
    //
    @Autowired
    private ILoginUser loginUseCase;

    @Autowired
    private IUpdateUserInfo updateUserInfo;


    @GetMapping
    @RolesAllowed({"Admin"})
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        GetAllUsersResponse response = iGetAllUsers.getAllUsers();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest userRequest) {
        CreateUserResponse response = createUser.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        deleteUser.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        LoginResponse resp = loginUseCase.login(req);
        return ResponseEntity.ok(resp);
    }

    @RolesAllowed({"Customer"})
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") final Long id){
        Optional<User> user = getUserById.getUserById(id);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }


    @RolesAllowed({"Customer"})
    @PutMapping("/update")
    public ResponseEntity<UpdateUserInfoResponse> updateUserInfo(@RequestBody @Valid UpdateUserInfoRequest request){
        UpdateUserInfoResponse response = updateUserInfo.updateUserInfo(request);
        return ResponseEntity.ok(response);
    }



}

