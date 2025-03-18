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
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length( max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Length( max = 50)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "address")
    private String dddress;

    @Column(name = "password")
    @Length(max = 150)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<UserRoleEntity> userRoles;
}
