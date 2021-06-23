package com.spiet.bleiny.api.users.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiet.bleiny.api.users.http.dto.AddressDTO;
import com.spiet.bleiny.api.users.utils.CreateUserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.spiet.bleiny.api.users.http.controllers.UserController;
import org.springframework.test.web.servlet.MockMvc;
import com.spiet.bleiny.api.users.services.IUserService;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.spiet.bleiny.api.users.http.dto.UserDTO;
import com.spiet.bleiny.shared.config.PasswordEncoderConfig;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserControllerTest {

    private final String BASE_URL = "/user";

    @MockBean
    private IUserService userService;

    @Autowired
    private MockMvc mockMvc;


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
    void shouldBeAbleCreateUser() throws Exception {
        var userDto = createDto();

        String json = new ObjectMapper().writeValueAsString(userDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(BASE_URL.concat("/register"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        BDDMockito.given(userService.create(Mockito.any(UserDTO.class))).willReturn(userDto);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("email").value(userDto.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("username").value(userDto.getUsername()));
    }
}
