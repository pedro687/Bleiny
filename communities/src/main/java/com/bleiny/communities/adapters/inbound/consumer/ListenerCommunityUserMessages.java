package com.bleiny.communities.adapters.inbound.consumer;

import com.bleiny.communities.CommunitiesApplication;
import com.bleiny.communities.adapters.inbound.consumer.dto.ReceiveUserMessageDTO;
import com.bleiny.communities.adapters.inbound.utils.UserConverter;
import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.UserRepositoryPort;
import com.bleiny.communities.application.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
public class ListenerCommunityUserMessages {

    private final ModelMapper modelMapper;

    private final UserServiceImpl userService;

    private UserConverter userConverter;

    public ListenerCommunityUserMessages(final ModelMapper modelMapper, final UserServiceImpl userService, final UserConverter userConverter) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userConverter = userConverter;
    }

  //  @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void listenerMessagesCommunity(String message) throws ApiException {
        log.info("Recebendo mensagem: {}", message);
        try {
            var messageConverter = new ObjectMapper().readValue(message, ReceiveUserMessageDTO.class);
            var messageToUser = userConverter.messageToUser(messageConverter);
            userService.createUser(messageToUser);
        } catch (Exception e) {
            log.error("Error ao receber mensagem: {}", e.getMessage());
            throw ApiException.internalError("Error on listener message", "Erro ao ouvir mensagem");
        }
    }

}
