package com.spiet.bleiny.api.users.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiet.bleiny.api.users.dto.AddressDTO;
import com.spiet.bleiny.api.users.dto.UserDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.spiet.bleiny.api.users.services.IUserService;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
    @DisplayName("Deve Criar um usuário")
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

    @Test
    @DisplayName("Não Deve Criar um usuário")
    void mayNotBeAbleToCreateAUser() throws Exception {
        String json = "";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(BASE_URL.concat("/register"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
