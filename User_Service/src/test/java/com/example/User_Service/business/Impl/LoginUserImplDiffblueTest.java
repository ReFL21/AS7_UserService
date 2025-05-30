package com.example.User_Service.business.Impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.User_Service.domain.LoginRequest;
import com.example.User_Service.repository.Role;
import com.example.User_Service.repository.UserEntity;
import com.example.User_Service.repository.UserRepository;
import com.example.User_Service.repository.UserRoleEntity;
import com.example.User_Service.security.JwtUtil;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LoginUserImpl.class, PasswordEncoder.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class LoginUserImplDiffblueTest {
    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Autowired
    private LoginUserImpl loginUserImpl;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Test login(LoginRequest)")
    @Tag("MaintainedByDiffblue")
    void testLogin() {
        // Arrange
        when(userRepository.findByUsername(Mockito.<String>any()))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        LoginRequest request = new LoginRequest();
        request.setPassword("iloveyou");
        request.setUsername("janedoe");

        // Act and Assert
        assertThrows(BadCredentialsException.class, () -> loginUserImpl.login(request));
        verify(userRepository).findByUsername(eq("janedoe"));
    }

    @Test
    @DisplayName("Test login(LoginRequest); given UserRepository findByUsername(String) return empty")
    @Tag("MaintainedByDiffblue")
    void testLogin_givenUserRepositoryFindByUsernameReturnEmpty() {
        // Arrange
        Optional<UserEntity> emptyResult = Optional.empty();
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(emptyResult);

        LoginRequest request = new LoginRequest();
        request.setPassword("iloveyou");
        request.setUsername("janedoe");

        // Act and Assert
        assertThrows(BadCredentialsException.class, () -> loginUserImpl.login(request));
        verify(userRepository).findByUsername(eq("janedoe"));
    }


    @Test
    @DisplayName("Test login(LoginRequest); given UserRepository findByUsername(String) return of UserEntity()")
    @Tag("MaintainedByDiffblue")
    void testLogin_givenUserRepositoryFindByUsernameReturnOfUserEntity() {
        // Arrange
        UserRoleEntity userRoles = new UserRoleEntity();
        userRoles.setId(1L);
        userRoles.setRole(Role.Customer);
        userRoles.setUser(new UserEntity());

        UserEntity user = new UserEntity();
        user.setAddress("42 Main St");
        user.setCity("Oxford");
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setUserRoles(userRoles);
        user.setUsername("janedoe");

        UserRoleEntity userRoles2 = new UserRoleEntity();
        userRoles2.setId(1L);
        userRoles2.setRole(Role.Customer);
        userRoles2.setUser(user);

        UserEntity userEntity = new UserEntity();
        userEntity.setAddress("42 Main St");
        userEntity.setCity("Oxford");
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setId(1L);
        userEntity.setName("Name");
        userEntity.setPassword("iloveyou");
        userEntity.setUserRoles(userRoles2);
        userEntity.setUsername("janedoe");
        Optional<UserEntity> ofResult = Optional.of(userEntity);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);

        LoginRequest request = new LoginRequest();
        request.setPassword("iloveyou");
        request.setUsername("janedoe");

        // Act and Assert
        assertThrows(BadCredentialsException.class, () -> loginUserImpl.login(request));
        verify(userRepository).findByUsername(eq("janedoe"));
    }


    @Test
    @DisplayName("Test login(LoginRequest); then calls getPassword()")
    @Tag("MaintainedByDiffblue")
    void testLogin_thenCallsGetPassword() {
        // Arrange
        UserRoleEntity userRoles = new UserRoleEntity();
        userRoles.setId(1L);
        userRoles.setRole(Role.Customer);
        userRoles.setUser(new UserEntity());

        UserEntity user = new UserEntity();
        user.setAddress("42 Main St");
        user.setCity("Oxford");
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setUserRoles(userRoles);
        user.setUsername("janedoe");

        UserRoleEntity userRoles2 = new UserRoleEntity();
        userRoles2.setId(1L);
        userRoles2.setRole(Role.Customer);
        userRoles2.setUser(user);
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserRoles()).thenThrow(new BadCredentialsException("Msg"));
        when(userEntity.getPassword()).thenReturn("iloveyou");
        doNothing().when(userEntity).setAddress(Mockito.<String>any());
        doNothing().when(userEntity).setCity(Mockito.<String>any());
        doNothing().when(userEntity).setEmail(Mockito.<String>any());
        doNothing().when(userEntity).setId(Mockito.<Long>any());
        doNothing().when(userEntity).setName(Mockito.<String>any());
        doNothing().when(userEntity).setPassword(Mockito.<String>any());
        doNothing().when(userEntity).setUserRoles(Mockito.<UserRoleEntity>any());
        doNothing().when(userEntity).setUsername(Mockito.<String>any());
        userEntity.setAddress("42 Main St");
        userEntity.setCity("Oxford");
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setId(1L);
        userEntity.setName("Name");
        userEntity.setPassword("iloveyou");
        userEntity.setUserRoles(userRoles2);
        userEntity.setUsername("janedoe");
        Optional<UserEntity> ofResult = Optional.of(userEntity);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        JwtUtil jwtUtil = new JwtUtil();
        LoginUserImpl loginUserImpl = new LoginUserImpl(userRepository, authenticationManager, jwtUtil,
                new LdapShaPasswordEncoder());

        LoginRequest request = new LoginRequest();
        request.setPassword("iloveyou");
        request.setUsername("janedoe");

        // Act and Assert
        assertThrows(BadCredentialsException.class, () -> loginUserImpl.login(request));
        verify(userEntity).getPassword();
        verify(userEntity).getUserRoles();
        verify(userEntity).setAddress(eq("42 Main St"));
        verify(userEntity).setCity(eq("Oxford"));
        verify(userEntity).setEmail(eq("jane.doe@example.org"));
        verify(userEntity).setId(eq(1L));
        verify(userEntity).setName(eq("Name"));
        verify(userEntity).setPassword(eq("iloveyou"));
        verify(userEntity).setUserRoles(isA(UserRoleEntity.class));
        verify(userEntity).setUsername(eq("janedoe"));
        verify(userRepository).findByUsername(eq("janedoe"));
    }
}
