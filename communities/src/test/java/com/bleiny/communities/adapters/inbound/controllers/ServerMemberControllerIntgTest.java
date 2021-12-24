package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.ServerMemberEnjoyDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServerMemberControllerIntgTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName(value = "POST /v1/server success")
    void postServerMember() {
        ServerMemberEnjoyDTO enjoy = ServerMemberEnjoyDTO.builder()
                .idCommunity(1L)
                .idUser(1L)
                .build();

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