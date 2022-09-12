package com.bleiny.communities.adapters.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.List;

@Configuration
@EnableKafka
@Slf4j
public class KafkaConfiguration {

    public DefaultErrorHandler defaultErrorHandler() {
        var fixedBackoff = new FixedBackOff(1000L, 2);
        var errorHandler = new DefaultErrorHandler(fixedBackoff);

        var ignoreExceptions = List.of(
            IllegalArgumentException.class
        );


        ignoreExceptions.forEach(errorHandler::addNotRetryableExceptions);

        errorHandler
                .setRetryListeners((consumerRecord, ex, deliveryAttempted) -> {
                    log.info("Failed record in retry Listener: {}, Delivery Attempted: {}", ex.getMessage(), deliveryAttempted);
                });

        return errorHandler;
    }


    @Bean
    ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(containerFactory, consumerFactory);

        containerFactory.setConcurrency(3);
        containerFactory.setCommonErrorHandler(defaultErrorHandler());


        return  containerFactory;
    }


}
