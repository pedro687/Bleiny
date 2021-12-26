package com.bleiny.communities.application.ports;

import com.bleiny.communities.application.domain.Room;

public interface RoomServicePort {
    Room createRoom(Room room);
}
