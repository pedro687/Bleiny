package com.bleiny.communities.application.services;

import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.UserRepositoryPort;
import com.bleiny.communities.application.ports.UserServicePort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class UserServiceImplUnitTest {

    UserServicePort userServicePort;

    @MockBean
    UserRepositoryPort userRepositoryPort;

    @MockBean
    ModelMapper modelmapper;

    @BeforeEach
    void setUp() {
        this.userServicePort = new UserServiceImpl(userRepositoryPort, modelmapper);
    }

    @Test
    @DisplayName("Should not create a user")
    void createUser() throws ApiException {
        Users user = null;

        Throwable exception = Assertions.catchThrowable(() -> userServicePort.createUser(user));

        Assertions.assertThat(exception).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("Should return a user")
    void findUserById() throws ApiException {
        var user = Users
                .builder()
                .id(1L)
                .username("Jon Doe")
                .uuid("13732e76-79b2-4597-be16-772f19d5227d")
                .build();

        Mockito.when(userRepositoryPort.findById(user.getId())).thenReturn(java.util.Optional.of(user));

        var findUser = userServicePort.findById(user.getId());

        Assertions.assertThat(findUser).isEqualTo(user);
    }

    @Test
    @DisplayName("Should not return a user")
    void findUserById_error() throws ApiException {
        var user = Users
                .builder()
                .id(1L)
                .username("Jon Doe")
                .uuid("13732e76-79b2-4597-be16-772f19d5227d")
                .build();

        //Mockito.when(userRepositoryPort.findById(user.getId())).thenReturn(java.util.Optional.empty());
        Throwable throwable = Assertions.catchThrowable(() -> userServicePort.findById(user.getId()));

        Assertions.assertThat(throwable).isInstanceOf(Exception.class);
    }
}