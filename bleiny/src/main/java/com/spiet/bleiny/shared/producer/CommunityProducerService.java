package com.spiet.bleiny.shared.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiet.bleiny.shared.domain.User;
import com.spiet.bleiny.shared.infra.ApiException;
import com.spiet.bleiny.shared.producer.dto.SendUserToCommunityMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class CommunityProducerService {


    private String topic = "bleiny_community_topic";

    private final KafkaTemplate<Long, String> kafkaTemplate;

    @Autowired
    private final ObjectMapper objectMapper;

    CommunityProducerService(final KafkaTemplate<Long, String> kafkaTemplate, final ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public ListenableFuture<SendResult<Long, String>> sendMessageToCommunityTopic(final SendUserToCommunityMessageDTO dto) throws ApiException, JsonProcessingException {
        var key = dto.getId();
        var json = objectMapper.writeValueAsString(dto);

        ProducerRecord<Long, String> producerRecordFactory = producerRecordFactory(key, json);
        ListenableFuture<SendResult<Long, String>> resultFuture = this.kafkaTemplate.send(producerRecordFactory);

        resultFuture.addCallback(new ListenableFutureCallback<SendResult<Long, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleError(key, json, ex);
            }

            @Override
            public void onSuccess(SendResult<Long, String> longStringSendResult) {
                handleSuccess(key, json, longStringSendResult);
            }
        });

        return resultFuture;

    }

    private void handleError(Long key, String value, Throwable ex) {
        try {
            throw ex;
        } catch (Throwable e) {
            log.info("ERROR ON SENT MESSAGE for the key: {}, and value: {} and partition is {}", key, value, e.getMessage());
        }

    }

    private void handleSuccess(Long key, String value, SendResult<Long, String> result) {
        log.info("Message sent with sucessfuly! Key: {}, value: {}, partition: {}", key, value, result.getRecordMetadata());
    }

    public ProducerRecord<Long, String> producerRecordFactory(Long key, String value) {
        //topic, partition, key, value
        return new ProducerRecord<>(this.topic, null, key, value);
    }
}
