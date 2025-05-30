package com.example.User_Service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.User_Service.repository.Role;
import com.example.User_Service.repository.UserEntity;
import com.example.User_Service.repository.UserRepository;
import com.example.User_Service.repository.UserRoleEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserDetailsServiceImpl.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class UserDetailsServiceImplDiffblueTest {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @MockitoBean
    private UserRepository userRepository;


    @Test
    @DisplayName("Test loadUserByUsername(String); given UserRepository findByUsername(String) return empty")
    @Tag("MaintainedByDiffblue")
    void testLoadUserByUsername_givenUserRepositoryFindByUsernameReturnEmpty() throws UsernameNotFoundException {
        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        Optional<UserEntity> emptyResult = Optional.empty();
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class,
                () -> (new UserDetailsServiceImpl(userRepository)).loadUserByUsername("janedoe"));
        verify(userRepository).findByUsername(eq("janedoe"));
    }


    @Test
    @DisplayName("Test loadUserByUsername(String); then calls getUserRoles()")
    @Tag("MaintainedByDiffblue")
    void testLoadUserByUsername_thenCallsGetUserRoles() throws UsernameNotFoundException {
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

        UserRoleEntity userRoles3 = new UserRoleEntity();
        userRoles3.setId(1L);
        userRoles3.setRole(Role.Customer);
        userRoles3.setUser(new UserEntity());

        UserEntity user2 = new UserEntity();
        user2.setAddress("42 Main St");
        user2.setCity("Oxford");
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setUserRoles(userRoles3);
        user2.setUsername("janedoe");

        UserRoleEntity userRoles4 = new UserRoleEntity();
        userRoles4.setId(1L);
        userRoles4.setRole(Role.Customer);
        userRoles4.setUser(user2);

        UserEntity user3 = new UserEntity();
        user3.setAddress("42 Main St");
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setId(1L);
        user3.setName("Name");
        user3.setPassword("iloveyou");
        user3.setUserRoles(userRoles4);
        user3.setUsername("janedoe");

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setId(1L);
        userRoleEntity.setRole(Role.Customer);
        userRoleEntity.setUser(user3);
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUsername()).thenThrow(new UsernameNotFoundException("Msg"));
        when(userEntity.getUserRoles()).thenReturn(userRoleEntity);
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
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userDetailsServiceImpl.loadUserByUsername("janedoe"));
        verify(userEntity).getUserRoles();
        verify(userEntity).getUsername();
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

    @Test
    @DisplayName("Test loadUserByUsername(String); then return Authorities size is one")
    @Tag("MaintainedByDiffblue")
    void testLoadUserByUsername_thenReturnAuthoritiesSizeIsOne() throws UsernameNotFoundException {
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

        // Act
        UserDetails actualLoadUserByUsernameResult = userDetailsServiceImpl.loadUserByUsername("janedoe");

        // Assert
        verify(userRepository).findByUsername(eq("janedoe"));
        Collection<? extends GrantedAuthority> authorities = actualLoadUserByUsernameResult.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities instanceof Set);
        assertTrue(actualLoadUserByUsernameResult instanceof User);
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
    }
}
