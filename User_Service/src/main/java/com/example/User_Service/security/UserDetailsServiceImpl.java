package com.example.User_Service.security;

import com.example.User_Service.repository.UserEntity;
import com.example.User_Service.repository.UserRepository;
import com.example.User_Service.repository.UserRoleEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));

        // Extract the single UserRoleEntity from your UserEntity:
        UserRoleEntity userRole = user.getUserRoles();  // <-- or getUserRole(), whatever your field is named

        // Get the enum value:
        String roleName = userRole.getRole().name();  // enum Role.Customer → "Customer"

        // Build one GrantedAuthority:
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + roleName);

        // Wrap it in a singleton list:
        List<SimpleGrantedAuthority> authorities =
                Collections.singletonList(authority);

        // Return the Spring Security User:
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
