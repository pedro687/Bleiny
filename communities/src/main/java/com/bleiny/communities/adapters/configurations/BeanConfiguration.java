package com.bleiny.communities.adapters.configurations;

import com.bleiny.communities.CommunitiesApplication;
import com.bleiny.communities.adapters.inbound.consumer.ListenerCommunityUserMessages;
import com.bleiny.communities.adapters.outbound.persistence.SpringDataCommunityRepository;
import com.bleiny.communities.application.ports.*;
import com.bleiny.communities.application.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan(basePackageClasses = CommunitiesApplication.class)
public class BeanConfiguration {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommunityServiceImpl communityService(CommunityRepositoryPort repository, ModelMapper modelMapper,
                                          @Lazy ServerMemberServicePort serverMemberServicePort, SpringDataCommunityRepository springDataCommunityRepository
    ) {
        return new CommunityServiceImpl(repository, modelMapper, serverMemberServicePort, springDataCommunityRepository);
    }

    @Bean
    UserServiceImpl userService(UserRepositoryPort repository, ModelMapper modelMapper) {
        return new UserServiceImpl(repository, modelMapper);
    }

    @Bean
    ServerMemberServiceImpl serverMemberService(ServerMemberRepositoryPort repositoryPort, CommunityServicePort communityServicePort,
                                                UserServicePort userRepositoryPort, ModelMapper modelMapper) {
        return new ServerMemberServiceImpl(repositoryPort, communityServicePort, userRepositoryPort, modelMapper);
    }

    @Bean
    RoomServiceImpl roomService(ModelMapper modelMapper, RoomRepositoryPort repositoryPort, CommunityServicePort communityServicePort) {
        return new RoomServiceImpl(modelMapper, repositoryPort, communityServicePort);
    }

    @Bean
    TagServerServiceImpl tagServerService(ModelMapper modelMapper, CommunityServicePort communityServicePort, TagServerRepositoryPort repository) {
        return new TagServerServiceImpl(modelMapper, communityServicePort, repository);
    }
}
