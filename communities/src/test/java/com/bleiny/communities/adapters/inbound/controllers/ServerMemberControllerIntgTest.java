package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.ServerMemberEnjoyDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.UserServicePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class ServerMemberControllerIntgTest {

    @SpyBean
    UserServicePort userServicePort;

    @Autowired
    TestRestTemplate restTemplate;

    @SpyBean
    CommunityServicePort communityServicePort;

    @Test
    @DisplayName(value = "POST /v1/server success")
    void postServerMember() throws ApiException {
        ServerMemberEnjoyDTO enjoy = ServerMemberEnjoyDTO.builder()
                .idCommunity(1L)
                .idUser(1L)
                .build();

        var user = Users.builder()
                .id(1L)
                .username("Jon Doe")
                .uuid("awdsaws-1asdwas-1asdwasdwas").build();

        var communityDTO = CommunityDTO.builder()
                .communityName("Rock and Roll")
                .communityLeaderId(1L)
                .description("awdasdwasdw")
                .build();

        userServicePort.createUser(user);
        Mockito.verify(userServicePort, Mockito.times(1)).createUser(Mockito.isA(Users.class));

        communityServicePort.createCommunity(communityDTO);
        Mockito.verify(communityServicePort, Mockito.times(1)).createCommunity(communityDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", MediaType.APPLICATION_JSON.toString());

        HttpEntity<ServerMemberEnjoyDTO> body = new HttpEntity<>(enjoy, httpHeaders);

        var res = restTemplate.exchange("/v1/server", HttpMethod.POST, body, void.class);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());

    }

    @Test
    @DisplayName(value = "POST /v1/server error")
    void postServerMemberError() {
        ServerMemberEnjoyDTO enjoy = null;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", MediaType.APPLICATION_JSON.toString());

        HttpEntity<ServerMemberEnjoyDTO> body = new HttpEntity<>(enjoy, httpHeaders);

        var res = restTemplate.exchange("/v1/server", HttpMethod.POST, body, void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    }
}