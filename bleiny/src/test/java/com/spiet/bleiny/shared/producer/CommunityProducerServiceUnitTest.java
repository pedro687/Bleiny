package com.spiet.bleiny.shared.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiet.bleiny.shared.infra.ApiException;
import com.spiet.bleiny.shared.producer.dto.SendUserToCommunityMessageDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CommunityProducerServiceUnitTest {

    @Spy
    ObjectMapper objectMapper;

    @Mock
    KafkaTemplate<Long, String> kafkaTemplate;

    @InjectMocks
    CommunityProducerService producerService;

    @Test
    @DisplayName("Testando o envio da mensagem com sucesso")
    public void testIfMessageSentWithSucessfuly() throws JsonProcessingException, ApiException, ExecutionException, InterruptedException {
        SendUserToCommunityMessageDTO dto = SendUserToCommunityMessageDTO
                .builder()
                .id(1L)
                .userName("PedroSpiet")
                .uuid(UUID.randomUUID().toString()).build();

        var message = objectMapper.writeValueAsString(dto);

        SettableListenableFuture settableListenableFuture = new SettableListenableFuture();

        ProducerRecord<Long, String> producerRecord = new ProducerRecord<>("bleiny_community_topic", dto.getId(), message);

        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition("bleiny_community_topic", 1),
                1,1,342,System.currentTimeMillis(), 1, 2);

        SendResult<Long, String> sendResult = new SendResult<Long, String>(producerRecord,recordMetadata);

        settableListenableFuture.set(sendResult);

        when(kafkaTemplate.send(isA(ProducerRecord.class))).thenReturn(settableListenableFuture);

        ListenableFuture<SendResult<Long, String>> listen = producerService.sendMessageToCommunityTopic(dto);

        SendResult<Long, String> result = listen.get();

        assert result.getRecordMetadata().partition() == 1;
    }
}
