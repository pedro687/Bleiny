package com.spiet.bleiny.shared.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiet.bleiny.shared.domain.User;
import com.spiet.bleiny.shared.infra.ApiException;
import com.spiet.bleiny.shared.producer.dto.SendUserToCommunityMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommunityProducerService {

    @Value("${kafka.topics.community_topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(SendUserToCommunityMessageDTO dto) throws ApiException {
        try {
            log.info("Send Message to Topic: {}", dto.toString());
            var message = new ObjectMapper().writeValueAsString(dto);
            kafkaTemplate.send(topic, message);
        } catch (JsonProcessingException e) {
            log.info("Erro ao converter mensagem: {}", e.getMessage());
            throw ApiException.internalError("Error on converter Message to community", "Falha ao converter mensagem");
        }
    }
}
