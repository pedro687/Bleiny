package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.ServerMemberEnjoyDTO;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.ServerMemberServicePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ServerMemberController.class)
@AutoConfigureMockMvc
class ServerMemberControllerUnitTest {

    @MockBean
    ServerMemberServicePort servicePort;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /v1/server should return 2xx")
    void enjoying_community_2xx() throws Exception {
        var enjoy = ServerMemberEnjoyDTO.builder()
                .idCommunity(1L)
                .idUser(1L)
                .build();

        var json = objectMapper.writeValueAsString(enjoy);

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/v1/server")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @DisplayName("POST /v1/server should return 4xx")
    void enjoying_community_4xx() throws Exception {
        String enjoy = "";

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/v1/server")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(enjoy);

        mockMvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
