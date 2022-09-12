package com.bleiny.communities.application.services;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import com.bleiny.communities.adapters.inbound.dtos.TagServerDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Tag;
import com.bleiny.communities.application.domain.TagServer;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityRepositoryPort;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.TagServerRepositoryPort;
import com.bleiny.communities.application.ports.TagServerServicePort;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CommunityServiceImplTest {

    CommunityServicePort communityServicePort;

    @MockBean
    CommunityRepositoryPort communityRepositoryPort;

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    TagServerRepositoryPort tagServerRepositoryPort;
    TagServerServicePort tagServerServicePort;

    @BeforeEach
    void setUp() {
        this.communityServicePort = new CommunityServiceImpl(communityRepositoryPort, modelMapper);
        this.modelMapper = new ModelMapper();
        this.tagServerServicePort = new TagServerServiceImpl(modelMapper, communityServicePort, tagServerRepositoryPort);

    }

    @Test
    @DisplayName("Creating Community")
    void should_create_community() throws ApiException {
        var communityDTO = CommunityDTO.builder()
                .communityLeaderId(1L)
                .communityName("DC Fans")
                .description("A Community to DC Fans")
                .build();

        var communityCreated = communityServicePort.createCommunity(communityDTO);

        Assertions.assertThat(communityCreated).isEqualTo(communityDTO);
    }

    @Test
    @DisplayName("Should be add servers in tag")
    void addTagTest() {
        Community communityDTO = Community
                .builder()
                .communityLeaderId(1L)
                .communityName("DC Fans")
                .description("Blabla")
                .id(1L)
                .build();

        Tag tag = Tag.builder()
                .tagName("Ação")
                .id(1L)
                .build();

        TagServerDTO tagServerDTO = TagServerDTO.builder()
                .tag(tag)
                .community(communityDTO)
                .build();


        Mockito.when(tagServerRepositoryPort.create(Mockito.any(TagServer.class))).thenReturn(tagServerDTO);

        var tagAdd = tagServerServicePort.addTag(tagServerDTO);

        Assertions.assertThat(tagAdd.getCommunity().getId()).isEqualTo(communityDTO.getId());
    }


    @Test
    @DisplayName("Should be error to add servers in tag")
    void addTagTestError() {
        TagServerDTO tagServerDTO = null;
        Throwable except = org.assertj.core.api.Assertions.catchThrowable(() -> tagServerServicePort.addTag(tagServerDTO));
        Assertions.assertThat(except).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("Should be able return communities by tag")
    void getCommunitiesByTagTest() throws ApiException {
        Long id = 1L;
        Community communityDTO = Community
                .builder()
                .communityLeaderId(1L)
                .communityName("DC Fans")
                .description("Blabla")
                .id(1L)
                .build();

        Tag tag = Tag.builder()
                .tagName("Ação")
                .id(1L)
                .build();

        ResponseTagServerDTO tagServerDTO = ResponseTagServerDTO.builder()
                .community(communityDTO)
                .build();
        var mock = List.of(tagServerDTO);
        PageRequest pageRequest = PageRequest.of(0, 1);

        Mockito.when(tagServerRepositoryPort.findCommunitiesByTag(Mockito.anyLong(), Mockito.any(PageRequest.class)))
                .thenReturn(mock);


        var communities = tagServerServicePort.filterByParameters(id, pageRequest, null, null).get(0);

        Page<ResponseTagServerDTO> pageable2 = new PageImpl<>(Arrays.asList(communities), pageRequest, mock.size());

        Assertions.assertThat(communities.getCommunity().getId()).isEqualTo(1L);
        Assertions.assertThat(pageable2.getNumberOfElements()).isEqualTo(1);
        Assertions.assertThat(pageable2.getSize()).isEqualTo(1);
        Assertions.assertThat(pageable2.getTotalPages()).isEqualTo(1);
        Assertions.assertThat(pageable2.getSize()).isEqualTo(1);
        Assertions.assertThat(pageable2.getContent().get(0).getCommunity().getId()).isEqualTo(1L);
    }
}