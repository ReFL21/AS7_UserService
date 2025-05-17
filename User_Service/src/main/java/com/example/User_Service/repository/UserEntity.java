package com.example.User_Service.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Length( max = 50)
    @Column(name = "Name")
    private String name;

    @NotBlank
    @Length( max = 50)
    @Column(name = "Username")
    private String username;

    @NotBlank
    @Column(name = "Email")
    private String email;

    @NotBlank
    @Column(name = "Address")
    private String address;

    @NotBlank
    @Column(name = "City")
    private String city;

    @Column(name = "Password")
    @Length(max = 150)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "User_Role", nullable = false)
    private UserRoleEntity userRoles;


}
