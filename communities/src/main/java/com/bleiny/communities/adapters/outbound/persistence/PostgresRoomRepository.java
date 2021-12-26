package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.CommunityEntity;
import com.bleiny.communities.adapters.outbound.persistence.entities.RoomEntity;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Room;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityRepositoryPort;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.RoomRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@Primary
public class PostgresRoomRepository implements RoomRepositoryPort {

    private final SpringDataRoomRepository repository;

    private final ModelMapper modelMapper;

    private final CommunityServicePort communityRepositoryPort;

    public PostgresRoomRepository(SpringDataRoomRepository repository, ModelMapper modelMapper, CommunityServicePort communityRepositoryPort) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.communityRepositoryPort = communityRepositoryPort;
    }

    @Override
    public Room createRoom(Room room, Community community) {
        var converter = modelMapper.map(room, RoomEntity.class);
        converter.setCommunity(modelMapper.map(community, CommunityEntity.class));
        repository.save(converter);
        return modelMapper.map(converter, Room.class);
    }

    @Override
    public List<Room> findRoomsByCommunity(Long id) throws ApiException {
        var rooms = repository.findAllByCommunityId(id);
        List<Room> roomsList = new ArrayList<>();

        rooms.forEach(r -> roomsList.add(modelMapper.map(r, Room.class)));

        return roomsList;
    }
}
