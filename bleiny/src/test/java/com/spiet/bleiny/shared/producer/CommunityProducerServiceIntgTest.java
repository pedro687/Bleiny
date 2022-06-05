package com.spiet.bleiny.shared.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiet.bleiny.api.users.dto.AddressDTO;
import com.spiet.bleiny.api.users.dto.UserDTO;
import com.spiet.bleiny.api.users.services.impl.UserService;
import com.spiet.bleiny.shared.infra.ApiException;
import lombok.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.isA;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = {"bleiny_community_topic"}, partitions = 3)
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}"})
@AutoConfigureTestDatabase
class CommunityProducerServiceIntgTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;

    @SpyBean
    UserService userService;

    Consumer<Long, String> consumer;

    @BeforeEach
    void setUp() {
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("group1", "true", embeddedKafkaBroker));
        consumer = new DefaultKafkaConsumerFactory<>(configs, new LongDeserializer(), new StringDeserializer()).createConsumer();
        embeddedKafkaBroker.consumeFromAllEmbeddedTopics(consumer);
    }

    @AfterEach
    void tearDown() {
        consumer.close();
    }

    @Test
    @Timeout(5)
    void createUserIntgTest() throws ApiException, JsonProcessingException {
        UserDTO userDTO = UserDTO.builder()
                .address(AddressDTO.builder()
                        .city("SP")
                        .uf("SP")
                        .country("BRAZIL")
                        .build())
                .username("PedroSpiet")
                .tellphone("13996403089")
                .password("batatinhafrita123")
                .email("pedrospiet@gmail.com.br")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);

        //when
        ResponseEntity<UserDTO> responseEntity = restTemplate.exchange("/user/register", HttpMethod.POST, request, UserDTO.class);
        Mockito.verify(userService, Mockito.times(1)).create(Mockito.isA(UserDTO.class));
        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        ConsumerRecord<Long, String> consumerRecord = KafkaTestUtils.getSingleRecord(consumer, "bleiny_community_topic");

        String value = consumerRecord.value();

        var receive = new ObjectMapper().readValue(value, ReceiveDTO.class);

        Assertions.assertEquals(receive.getUserName(), ReceiveDTO.builder()
                .userName("PedroSpiet").build().getUserName());
    }

}


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
class ReceiveDTO {
    private Long id;
    private String userName;
    private String uuid;

}