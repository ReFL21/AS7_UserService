package com.example.User_Service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserInfoResponse {

    private long id;
    private String name;
    private String username;
    private String email;
    private String address;
    private String city;

}
