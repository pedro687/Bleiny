package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.TagServerDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Tag;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TagControllerIntgTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("POST /v1/tag")
    void post_tag_controller() {
        TagServerDTO tagServerDTO = TagServerDTO.builder()
                .tag(Tag.builder().tagName("Horror").id(1L).build())
                .community(Community.builder().communityName("Horror fans").id(1L).communityLeaderId(1L).build()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<TagServerDTO> body = new HttpEntity<TagServerDTO>(tagServerDTO, headers);

        var res = restTemplate.exchange("/v1/tag", HttpMethod.POST, body, Void.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}