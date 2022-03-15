package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.RoomDTO;
import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.UserServicePort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class RoomControllerIntgTest {

    @SpyBean
    CommunityServicePort communityServicePort;

    @SpyBean
    UserServicePort userServicePort;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("POST /v1/room Should return 200 ")
    void postRoom() throws ApiException {
        Users users = Users.builder()
                .id(55L)
                .username("Jon DOe")
                .uuid("adwasdwas-awdasdwasd-wdasdawds")
                .build();

        userServicePort.createUser(users);
        Mockito.verify(userServicePort, Mockito.times(1)).createUser(Mockito.isA(Users.class));

        RoomDTO roomDTO = RoomDTO.builder()
                .roomName("Down with the Sickness")
                .communityId(1L)
                .isVoice(true)
                .build();

        CommunityDTO communityDTO = CommunityDTO.builder()
                .communityName("System of a down")
                .communityLeaderId(55L)
                .description("A Community to fans of system of a down").build();

        communityServicePort.createCommunity(communityDTO);
        Mockito.verify(communityServicePort, Mockito.times(1)).createCommunity(Mockito.isA(CommunityDTO.class));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<RoomDTO> httpEntity = new HttpEntity<>(roomDTO, httpHeaders);

        var res = restTemplate.exchange("/v1/room", HttpMethod.POST, httpEntity, RoomDTO.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getRooms() {
        Long idCommunity = 1L;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<RoomDTO> httpEntity = new HttpEntity<>(null, httpHeaders);

        var res = restTemplate.exchange("/v1/room/" + idCommunity, HttpMethod.GET, httpEntity, List.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}