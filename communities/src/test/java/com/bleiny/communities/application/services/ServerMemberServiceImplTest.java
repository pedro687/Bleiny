package com.bleiny.communities.application.services;

import com.bleiny.communities.adapters.inbound.dtos.ServerMemberEnjoyDTO;
import com.bleiny.communities.application.ports.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ServerMemberServiceImplTest {

    ServerMemberServiceImpl serverMemberService;

    @Test
    void should_not_join_to_community() {
        ServerMemberEnjoyDTO dto = ServerMemberEnjoyDTO.builder()
                .idCommunity(1L)
                .idUser(1L)
                .build();

        //Mockito.when(serverMemberRepositoryPort.save()).thenReturn();

        Throwable except = org.assertj.core.api.Assertions.catchThrowable(() -> serverMemberService.enjoyCommunity(dto));

        Assertions.assertThat(except).isInstanceOf(Exception.class);
    }

    @Test
    void member_already_in_Community_exception() {
        Long idUser = 1L, idCommunity = 1L;

        Throwable except = org.assertj.core.api.Assertions.catchThrowable(() -> serverMemberService.memberAlreadyInCommunity(idUser, idCommunity));

        Assertions.assertThat(except).isInstanceOf(Exception.class);

    }
}