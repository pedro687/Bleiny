package com.bleiny.communities.application.ports;

import com.bleiny.communities.application.domain.Room;
import com.bleiny.communities.application.exceptions.ApiException;

import java.util.List;

public interface RoomServicePort {
    Room createRoom(Room room) throws ApiException;
    List<Room> findRoomsByCommunityId(Long id) throws ApiException;
}
