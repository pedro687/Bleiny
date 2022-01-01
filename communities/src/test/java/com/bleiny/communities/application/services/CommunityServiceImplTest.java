package com.bleiny.communities.application.services;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityRepositoryPort;
import com.bleiny.communities.application.ports.CommunityServicePort;
import org.assertj.core.api.Assertions;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CommunityServiceImplTest {

    CommunityServicePort communityServicePort;

    @MockBean
    CommunityRepositoryPort communityRepositoryPort;

    @MockBean
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        this.communityServicePort = new CommunityServiceImpl(communityRepositoryPort, modelMapper);
        this.modelMapper = new ModelMapper();
    }

    @Test
    @DisplayName("Creating Community")
    void should_create_community() throws ApiException {
        var communityDTO = CommunityDTO.builder()
                .communityLeaderId(1L)
                .communityName("DC Fans")
                .description("A Community to DC Fans")
                .build();

        var mock = modelMapper.map(communityDTO, Community.class);

        Mockito.when(communityRepositoryPort.save(Mockito.any(Community.class))).thenReturn(mock);

        var communityCreated = communityServicePort.createCommunity(communityDTO);

        Assertions.assertThat(communityCreated).isEqualTo(communityDTO);
    }
}