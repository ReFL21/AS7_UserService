package com.example.User_Service.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_role")
@Entity
@Data
@Builder
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_role")
    private Long id;

    @NotNull
    @Column(name = "User_Role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "User_id")  // Ensure this matches the actual column name in the user_role table
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserEntity user;
}
