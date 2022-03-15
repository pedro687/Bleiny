package com.bleiny.communities.application.ports;

import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Room;
import com.bleiny.communities.application.exceptions.ApiException;

import java.util.List;
import java.util.Optional;

public interface RoomRepositoryPort {
    Room createRoom(Room room, Community community);
    List<Room> findRoomsByCommunity(Long id) throws ApiException;
}
