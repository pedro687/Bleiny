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

import java.util.Optional;

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

    @Override
    public Optional<Users> findById(Long id) throws ApiException {
        try {
            var user = userRepository.findById(id).orElseThrow(() ->
                    ApiException.notFound("Error on find user", "Usuário não existe na nossa base de dados"));

            return Optional.of(modelMapper.map(user, Users.class));
        } catch (Exception e) {
            throw ApiException.internalError("Error on find User", "Erro ao Encontrar usuário");
        }
    }
}
