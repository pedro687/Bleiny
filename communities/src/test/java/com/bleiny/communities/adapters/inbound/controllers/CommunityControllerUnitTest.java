package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.CommunitiesApplication;
import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommunityController.class)
@AutoConfigureMockMvc
class CommunityControllerUnitTest {

    @MockBean
    CommunityServicePort servicePort;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /v1/community should return 2xx")
    void creating_community_2xx() throws Exception {
        CommunityDTO dto = CommunityDTO.builder()
                .communityName("DC Fans")
                .communityLeaderId(1L)
                .description("A community to DC Fans").build();

        BDDMockito.given(servicePort.createCommunity(BDDMockito.any(CommunityDTO.class))).willReturn(dto);

        var body = objectMapper.writeValueAsString(dto);

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/v1/community")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("communityLeaderId").value(1));
    }

    @Test
    @DisplayName("POST /v1/community should return 4xx")
    void creating_community_4xx() throws Exception {
        CommunityDTO communityDTO = null;

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/v1/community")
                .accept(MediaType.APPLICATION_JSON)
                .content(String.valueOf(communityDTO))
                .contentType(MediaType.APPLICATION_JSON);


        mockMvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
