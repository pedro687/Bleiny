package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.RoomDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomControllerIntgTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("POST /v1/room Should return 200 ")
    void postRoom() {
        RoomDTO roomDTO = RoomDTO.builder()
                .roomName("Down with the Sickness")
                .communityId(1L)
                .isVoice(true)
                .build();

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

        var res = restTemplate.exchange("/v1/room/"+idCommunity, HttpMethod.GET, httpEntity, List.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}