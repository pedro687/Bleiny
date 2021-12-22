package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.application.ports.CommunityServicePort;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommunityControllerIntegrationTest {

    private final String BASE_URL = "/user";

    @Autowired
    TestRestTemplate request;


    @Test
    @DisplayName("POST /v1/community")
    void postCommunity() {
        //given
        var communityDTO = CommunityDTO.builder()
                .communityLeaderId(1L)
                .communityName("DC Fans")
                .description("A Community to DC Fans")
                .build();
        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", MediaType.APPLICATION_JSON.toString());

        HttpEntity<CommunityDTO> body = new HttpEntity<>(communityDTO, httpHeaders);

        var res = request.exchange("/v1/community", HttpMethod.POST, body ,CommunityDTO.class);
        //then
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
        Assertions.assertEquals(res.getBody(), communityDTO);
    }

    @Test
    @DisplayName("Post community error")
    void postCommunity_error() {
        CommunityDTO communityDTO = null;

        var headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<CommunityDTO> body = new HttpEntity<>(communityDTO, headers);

        var res = request.exchange("/v1/community", HttpMethod.POST, body , CommunityDTO.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    }
}