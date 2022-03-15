package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.RoomDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Room;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.RoomRepositoryPort;
import com.bleiny.communities.application.ports.RoomServicePort;
import com.bleiny.communities.application.services.RoomServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RoomController.class)
@AutoConfigureMockMvc
class RoomControllerUnitTest {

    @MockBean
    RoomServicePort roomServicePort;

    @MockBean
    ModelMapper modelMapper;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    void should_post_room() throws Exception {
        RoomDTO roomDTO = RoomDTO
                .builder()
                .roomName("DC Fans")
                .isVoice(false)
                .communityId(1L)
                .build();

        var json = new ObjectMapper().writeValueAsString(roomDTO);

        Community community = Community.builder().communityLeaderId(1L)
                        .communityName("DC Members")
                                .description("A community to DC Fans")
                                        .build();

        Mockito.when(roomServicePort.createRoom(modelMapper.map(roomDTO, Room.class))).thenReturn(modelMapper.map(roomDTO, Room.class));

        var createRoom = roomServicePort.createRoom(modelMapper.map(roomDTO, Room.class));

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/v1/room")
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}