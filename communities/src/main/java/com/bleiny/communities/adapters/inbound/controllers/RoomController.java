package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.RoomDTO;
import com.bleiny.communities.adapters.inbound.resources.RoomResource;
import com.bleiny.communities.application.domain.Room;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.RoomServicePort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class RoomController implements RoomResource {

    private final RoomServicePort roomServicePort;

    private ModelMapper modelMapper;

    public RoomController(RoomServicePort roomServicePort, ModelMapper modelMapper) {
        this.roomServicePort = roomServicePort;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<Room> postRoom(@RequestBody RoomDTO dto) throws ApiException {
        var createdRoom = roomServicePort.createRoom(modelMapper.map(dto, Room.class));
        return ResponseEntity.ok().body(createdRoom);
    }

    @Override
    public ResponseEntity<List<Room>> getRooms(Long id) throws ApiException {
        return ResponseEntity.ok(roomServicePort.findRoomsByCommunityId(id));
    }
}
