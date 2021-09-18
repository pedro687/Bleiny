package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.UserEntity;
import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.UserRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Slf4j
public class PostgresUserRepository implements UserRepositoryPort {

    private final SpringDataUserRepository userRepository;

    private final ModelMapper modelMapper;

    public PostgresUserRepository(SpringDataUserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(Users user) throws ApiException {
        try {
            var converter = modelMapper.map(user, UserEntity.class);
            userRepository.save(converter);
        } catch (Exception e) {
            throw ApiException.internalError("Error on save User", "Erro ao salvar usuário");
        }
    }
}
