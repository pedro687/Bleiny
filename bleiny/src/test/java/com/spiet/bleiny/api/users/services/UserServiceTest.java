package com.spiet.bleiny.api.users.services;

import com.spiet.bleiny.api.users.dto.AddressDTO;
import com.spiet.bleiny.api.users.dto.UserDTO;
import com.spiet.bleiny.api.users.services.impl.UserService;
import com.spiet.bleiny.shared.config.PasswordEncoderConfig;
import com.spiet.bleiny.shared.domain.User;
import com.spiet.bleiny.shared.infra.ApiException;
import com.spiet.bleiny.shared.producer.CommunityProducerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.spiet.bleiny.api.users.repositories.UserRepository;
import com.spiet.bleiny.api.users.utils.UserConverter;
import com.spiet.bleiny.shared.exceptions.EmailAlreadyExistsException;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "test")
public class UserServiceTest {

    private IUserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserConverter userConverter;

    @MockBean
    private IAddressService addressService;

    @MockBean
    private CommunityProducerService communityProducerService;

    @BeforeEach
    public void setUp() {
        this.userService = new UserService(userRepository, passwordEncoder , userConverter, addressService, communityProducerService);
    }

    public UserDTO createDto() {
        UserDTO userDTO = new UserDTO();
        PasswordEncoderConfig passwordEncoderConfig = new PasswordEncoderConfig();
        userDTO.setPassword(passwordEncoderConfig.passwordEncoder().encode("12345"));
        userDTO.setUsername("Jon Doe");
        userDTO.setEmail("JonDoe@email.com");
        userDTO.setTellphone("13996403088");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setUf("SP");
        addressDTO.setCity("Itanhaém");
        addressDTO.setCountry("Brazil");
        userDTO.setAddress(addressDTO);

        return userDTO;
    }

    @Test
    @DisplayName("Deve Criar um usuário")
    void testCreateUser() throws ApiException {
        var dto = createDto();
        var converter = new ModelMapper().map(dto, User.class);
        Mockito.when(userRepository.save(converter)).thenReturn(converter);

        var createdUser = userService.create(dto);

        Assertions.assertEquals(createdUser.getEmail(), "JonDoe@email.com");
    }

    @Test
    @DisplayName("Deve Criar um usuário com o mesmo email")
    void testMayNotBeAbleToCreateAUserWithSameEmail() {
        var dto = createDto();

        Mockito.when(userRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        Throwable error = Assertions.assertThrows(EmailAlreadyExistsException.class,
                () -> userService.create(dto),
                "Email Already Exists!");

        Assertions.assertEquals("Email Already Exists!", error.getMessage());
    }
}
