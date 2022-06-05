package com.spiet.bleiny.shared.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("local")
public class CreateTopic {

    @Bean
    public NewTopic bleinyCommunityTopic() {
        return TopicBuilder.name("bleiny_community_topic")
                .partitions(3)
                .replicas(1)
                .build();
    }
}