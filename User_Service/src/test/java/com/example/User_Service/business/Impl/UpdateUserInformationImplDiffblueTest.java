package com.example.User_Service.business.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.User_Service.domain.UpdateUserInfoRequest;
import com.example.User_Service.domain.UpdateUserInfoResponse;
import com.example.User_Service.repository.Role;
import com.example.User_Service.repository.UserEntity;
import com.example.User_Service.repository.UserRepository;
import com.example.User_Service.repository.UserRoleEntity;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UpdateUserInformationImpl.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class UpdateUserInformationImplDiffblueTest {
    @Autowired
    private UpdateUserInformationImpl updateUserInformationImpl;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Test updateUserInfo(UpdateUserInfoRequest); given UserRepository findByUsername(String) return empty")
    @Tag("MaintainedByDiffblue")
    void testUpdateUserInfo_givenUserRepositoryFindByUsernameReturnEmpty() {
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

        UserEntity user2 = new UserEntity();
        user2.setAddress("42 Main St");
        user2.setCity("Oxford");
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setUserRoles(new UserRoleEntity());
        user2.setUsername("janedoe");

        UserRoleEntity userRoles3 = new UserRoleEntity();
        userRoles3.setId(1L);
        userRoles3.setRole(Role.Customer);
        userRoles3.setUser(user2);

        UserEntity user3 = new UserEntity();
        user3.setAddress("42 Main St");
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setId(1L);
        user3.setName("Name");
        user3.setPassword("iloveyou");
        user3.setUserRoles(userRoles3);
        user3.setUsername("janedoe");

        UserRoleEntity userRoles4 = new UserRoleEntity();
        userRoles4.setId(1L);
        userRoles4.setRole(Role.Customer);
        userRoles4.setUser(user3);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setAddress("42 Main St");
        userEntity2.setCity("Oxford");
        userEntity2.setEmail("jane.doe@example.org");
        userEntity2.setId(1L);
        userEntity2.setName("Name");
        userEntity2.setPassword("iloveyou");
        userEntity2.setUserRoles(userRoles4);
        userEntity2.setUsername("janedoe");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save(Mockito.<UserEntity>any())).thenReturn(userEntity2);
        Optional<UserEntity> emptyResult = Optional.empty();
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(emptyResult);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        UpdateUserInformationImpl updateUserInformationImpl = new UpdateUserInformationImpl(userRepository);

        // Act
        UpdateUserInfoResponse actualUpdateUserInfoResult = updateUserInformationImpl
                .updateUserInfo(new UpdateUserInfoRequest());

        // Assert
        verify(userRepository).findByUsername(isNull());
        verify(userRepository).findById(eq(0L));
        verify(userRepository).save(isA(UserEntity.class));
        assertEquals("42 Main St", actualUpdateUserInfoResult.getAddress());
        assertEquals("Name", actualUpdateUserInfoResult.getName());
        assertEquals("Oxford", actualUpdateUserInfoResult.getCity());
        assertEquals("jane.doe@example.org", actualUpdateUserInfoResult.getEmail());
        assertEquals("janedoe", actualUpdateUserInfoResult.getUsername());
        assertEquals(1L, actualUpdateUserInfoResult.getId());
    }

    @Test
    @DisplayName("Test updateUserInfo(UpdateUserInfoRequest); given UserRepository save(Object) throw IllegalArgumentException(String) with 'foo'")
    @Tag("MaintainedByDiffblue")
    void testUpdateUserInfo_givenUserRepositorySaveThrowIllegalArgumentExceptionWithFoo() {
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

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setAddress("42 Main St");
        userEntity2.setCity("Oxford");
        userEntity2.setEmail("jane.doe@example.org");
        userEntity2.setId(1L);
        userEntity2.setName("Name");
        userEntity2.setPassword("iloveyou");
        userEntity2.setUserRoles(userRoles4);
        userEntity2.setUsername("janedoe");
        Optional<UserEntity> ofResult2 = Optional.of(userEntity2);
        when(userRepository.save(Mockito.<UserEntity>any())).thenThrow(new IllegalArgumentException("foo"));
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        UpdateUserInfoRequest request = UpdateUserInfoRequest.builder()
                .city("Oxford")
                .email("jane.doe@example.org")
                .id(1L)
                .name("Name")
                .username("janedoe")
                .build();

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> updateUserInformationImpl.updateUserInfo(request));
        verify(userRepository).findByUsername(eq("janedoe"));
        verify(userRepository).findById(eq(1L));
        verify(userRepository).save(isA(UserEntity.class));
    }

    @Test
    @DisplayName("Test updateUserInfo(UpdateUserInfoRequest); then return Address is '42 Main St'")
    @Tag("MaintainedByDiffblue")
    void testUpdateUserInfo_thenReturnAddressIs42MainSt() {
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

        UserEntity user2 = new UserEntity();
        user2.setAddress("42 Main St");
        user2.setCity("Oxford");
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setUserRoles(new UserRoleEntity());
        user2.setUsername("janedoe");

        UserRoleEntity userRoles3 = new UserRoleEntity();
        userRoles3.setId(1L);
        userRoles3.setRole(Role.Customer);
        userRoles3.setUser(user2);

        UserEntity user3 = new UserEntity();
        user3.setAddress("42 Main St");
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setId(1L);
        user3.setName("Name");
        user3.setPassword("iloveyou");
        user3.setUserRoles(userRoles3);
        user3.setUsername("janedoe");

        UserRoleEntity userRoles4 = new UserRoleEntity();
        userRoles4.setId(1L);
        userRoles4.setRole(Role.Customer);
        userRoles4.setUser(user3);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setAddress("42 Main St");
        userEntity2.setCity("Oxford");
        userEntity2.setEmail("jane.doe@example.org");
        userEntity2.setId(1L);
        userEntity2.setName("Name");
        userEntity2.setPassword("iloveyou");
        userEntity2.setUserRoles(userRoles4);
        userEntity2.setUsername("janedoe");
        when(userRepository.save(Mockito.<UserEntity>any())).thenReturn(userEntity2);
        Optional<UserEntity> emptyResult = Optional.empty();
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(emptyResult);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        UpdateUserInfoResponse actualUpdateUserInfoResult = updateUserInformationImpl
                .updateUserInfo(new UpdateUserInfoRequest());

        // Assert
        verify(userRepository).findByUsername(isNull());
        verify(userRepository).findById(eq(0L));
        verify(userRepository).save(isA(UserEntity.class));
        assertEquals("42 Main St", actualUpdateUserInfoResult.getAddress());
        assertEquals("Name", actualUpdateUserInfoResult.getName());
        assertEquals("Oxford", actualUpdateUserInfoResult.getCity());
        assertEquals("jane.doe@example.org", actualUpdateUserInfoResult.getEmail());
        assertEquals("janedoe", actualUpdateUserInfoResult.getUsername());
        assertEquals(1L, actualUpdateUserInfoResult.getId());
    }

    /**
     * Test {@link UpdateUserInformationImpl#updateUserInfo(UpdateUserInfoRequest)}.
     * <ul>
     *   <li>Then return Address is {@code 42 Main St}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UpdateUserInformationImpl#updateUserInfo(UpdateUserInfoRequest)}
     */
    @Test
    @DisplayName("Test updateUserInfo(UpdateUserInfoRequest); then return Address is '42 Main St'")
    @Tag("MaintainedByDiffblue")
    void testUpdateUserInfo_thenReturnAddressIs42MainSt2() {
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

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setAddress("42 Main St");
        userEntity2.setCity("Oxford");
        userEntity2.setEmail("jane.doe@example.org");
        userEntity2.setId(1L);
        userEntity2.setName("Name");
        userEntity2.setPassword("iloveyou");
        userEntity2.setUserRoles(userRoles4);
        userEntity2.setUsername("janedoe");
        Optional<UserEntity> ofResult2 = Optional.of(userEntity2);

        UserEntity user3 = new UserEntity();
        user3.setAddress("42 Main St");
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setId(1L);
        user3.setName("Name");
        user3.setPassword("iloveyou");
        user3.setUserRoles(new UserRoleEntity());
        user3.setUsername("janedoe");

        UserRoleEntity userRoles5 = new UserRoleEntity();
        userRoles5.setId(1L);
        userRoles5.setRole(Role.Customer);
        userRoles5.setUser(user3);

        UserEntity user4 = new UserEntity();
        user4.setAddress("42 Main St");
        user4.setCity("Oxford");
        user4.setEmail("jane.doe@example.org");
        user4.setId(1L);
        user4.setName("Name");
        user4.setPassword("iloveyou");
        user4.setUserRoles(userRoles5);
        user4.setUsername("janedoe");

        UserRoleEntity userRoles6 = new UserRoleEntity();
        userRoles6.setId(1L);
        userRoles6.setRole(Role.Customer);
        userRoles6.setUser(user4);

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setAddress("42 Main St");
        userEntity3.setCity("Oxford");
        userEntity3.setEmail("jane.doe@example.org");
        userEntity3.setId(1L);
        userEntity3.setName("Name");
        userEntity3.setPassword("iloveyou");
        userEntity3.setUserRoles(userRoles6);
        userEntity3.setUsername("janedoe");
        when(userRepository.save(Mockito.<UserEntity>any())).thenReturn(userEntity3);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        UpdateUserInfoRequest request = UpdateUserInfoRequest.builder()
                .city("Oxford")
                .email("jane.doe@example.org")
                .id(1L)
                .name("Name")
                .username("janedoe")
                .build();

        // Act
        UpdateUserInfoResponse actualUpdateUserInfoResult = updateUserInformationImpl.updateUserInfo(request);

        // Assert
        verify(userRepository).findByUsername(eq("janedoe"));
        verify(userRepository).findById(eq(1L));
        verify(userRepository).save(isA(UserEntity.class));
        assertEquals("42 Main St", actualUpdateUserInfoResult.getAddress());
        assertEquals("Name", actualUpdateUserInfoResult.getName());
        assertEquals("Oxford", actualUpdateUserInfoResult.getCity());
        assertEquals("jane.doe@example.org", actualUpdateUserInfoResult.getEmail());
        assertEquals("janedoe", actualUpdateUserInfoResult.getUsername());
        assertEquals(1L, actualUpdateUserInfoResult.getId());
    }

    /**
     * Test {@link UpdateUserInformationImpl#updateUserInfo(UpdateUserInfoRequest)}.
     * <ul>
     *   <li>Then throw {@link IllegalArgumentException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UpdateUserInformationImpl#updateUserInfo(UpdateUserInfoRequest)}
     */
    @Test
    @DisplayName("Test updateUserInfo(UpdateUserInfoRequest); then throw IllegalArgumentException")
    @Tag("MaintainedByDiffblue")
    void testUpdateUserInfo_thenThrowIllegalArgumentException() {
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

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> updateUserInformationImpl.updateUserInfo(new UpdateUserInfoRequest()));
        verify(userRepository).findByUsername(isNull());
    }
}
