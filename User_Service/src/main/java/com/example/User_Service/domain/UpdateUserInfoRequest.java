package com.example.User_Service.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserInfoRequest {
    @NotNull
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
}
