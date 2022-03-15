package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.TagServerDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Tag;
import com.bleiny.communities.application.domain.TagServer;
import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.PostgresTagRepositoryPort;
import com.bleiny.communities.application.ports.TagServerRepositoryPort;
import com.bleiny.communities.application.ports.UserServicePort;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class TagControllerIntgTest {

    @Autowired
    TestRestTemplate restTemplate;

    @SpyBean
    CommunityServicePort communityServicePort;

    @SpyBean
    UserServicePort userServicePort;

    @SpyBean
    PostgresTagRepositoryPort postgresTagRepositoryPort;

    @Test
    @DisplayName("POST /v1/tag")
    void post_tag_controller() throws ApiException {
        Users users = Users.builder()
                .username("Jon Doe")
                .id(1L)
                .uuid("asdaws-asdas-dawads")
                .build();

        userServicePort.createUser(users);
        Mockito.verify(userServicePort, Mockito.times(1)).createUser(users);

        CommunityDTO communityDTO = CommunityDTO.builder()
                .communityName("Rock and Roll")
                .communityLeaderId(1L)
                .description("asdawsd")
                .build();

        Community community = Community.builder()
                .communityName(communityDTO.getCommunityName())
                .communityLeaderId(communityDTO.getCommunityLeaderId())
                .id(1L).build();

        communityServicePort.createCommunity(communityDTO);
        Mockito.verify(communityServicePort, Mockito.times(1)).createCommunity(communityDTO);

        Tag tag = Tag.builder()
                .id(1L)
                .tagName("Horror")
                .build();

        postgresTagRepositoryPort.create(tag);
        Mockito.verify(postgresTagRepositoryPort, Mockito.times(1)).create(Mockito.isA(Tag.class));

        TagServer tagServer = TagServer.builder()
                .tag(tag)
                .community(community).build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", "application/json");

        HttpEntity<TagServer> httpEntity = new HttpEntity<TagServer>(tagServer, httpHeaders);

        var res = restTemplate.exchange("/v1/tag", HttpMethod.POST, httpEntity, Void.class);

        Assert.assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
    }
}