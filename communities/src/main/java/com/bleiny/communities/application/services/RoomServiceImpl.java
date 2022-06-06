package com.bleiny.communities.application.services;

import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Room;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.RoomRepositoryPort;
import com.bleiny.communities.application.ports.RoomServicePort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.List;
@Slf4j
public class RoomServiceImpl implements RoomServicePort {

    private final ModelMapper modelMapper;

    private final RoomRepositoryPort repositoryPort;

    private final CommunityServicePort communityServicePort;

    public RoomServiceImpl(ModelMapper modelMapper, RoomRepositoryPort repositoryPort, CommunityServicePort communityServicePort) {
        this.modelMapper = modelMapper;
        this.repositoryPort = repositoryPort;
        this.communityServicePort = communityServicePort;
    }

    @Override
    public Room createRoom(Room room) throws ApiException {
        log.info("Criando sala");
        var community = communityServicePort.findById(room.getCommunityId());
        return repositoryPort.createRoom(room, modelMapper.map(community, Community.class));
    }

    @Override
    public List<Room> findRoomsByCommunityId(Long id) throws ApiException {
        return repositoryPort.findRoomsByCommunity(id);
    }


}
