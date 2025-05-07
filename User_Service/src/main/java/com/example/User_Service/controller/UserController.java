package com.example.User_Service.controller;
import com.example.User_Service.business.ICreateUser;
import com.example.User_Service.business.IDeleteUser;
import com.example.User_Service.business.IGetAllUsers;
import com.example.User_Service.business.ILoginUser;
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

@CrossOrigin(origins = {"http://localhost:8083"})
@RequestMapping("/users")
@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    @Autowired
    private IGetAllUsers iGetAllUsers;

    //
    @Autowired
    private ICreateUser createUser;
    //
    @Autowired
    private IDeleteUser deleteUser;
    //
    @Autowired
    private ILoginUser loginUseCase;


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
}

