package com.bleiny.communities.adapters.inbound.consumer;

import com.bleiny.communities.adapters.inbound.consumer.dto.ReceiveUserMessageDTO;
import com.bleiny.communities.adapters.outbound.persistence.SpringDataUserRepository;
import com.bleiny.communities.adapters.outbound.persistence.entities.UserEntity;
import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.services.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@EmbeddedKafka(topics = {"bleiny_community_topic"}, partitions = 3)
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}"
        , "spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}"})
@AutoConfigureTestDatabase
public class ListenerCommunityUserMessagesTest {

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    KafkaTemplate<Long, String> kafkaTemplate;

    @Autowired
    KafkaListenerEndpointRegistry endpointRegistry;

    @SpyBean
    ListenerCommunityUserMessages listener;

    @SpyBean
    UserServiceImpl userService;

    @Autowired
    SpringDataUserRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        for (MessageListenerContainer messageListener: endpointRegistry.getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListener, embeddedKafkaBroker.getPartitionsPerTopic());
        }
    }

    @Test()
    @DisplayName("Integration Test to Listener")
    void eventListener() throws JsonProcessingException, ExecutionException, InterruptedException, ApiException {
        var dto = ReceiveUserMessageDTO.builder()
                .id(1L)
                .userName("PedroSpiet")
                .uuid("f9178b91-072b-4f8b-a4e8-b0306db5379f")
                .build();

        var json = objectMapper.writeValueAsString(dto);

        kafkaTemplate.sendDefault(json).get();

        //when
        CountDownLatch latch = new CountDownLatch(1);
        latch.await(3, TimeUnit.SECONDS);

        Mockito.verify(listener, Mockito.times(1)).listenerMessagesCommunity(Mockito.any(String.class));
        Mockito.verify(userService, Mockito.times(1)).createUser(Mockito.any(Users.class));

       var user = repository.findById(dto.getId()).get();

       assert user.getId() == 1L;
    }

}
