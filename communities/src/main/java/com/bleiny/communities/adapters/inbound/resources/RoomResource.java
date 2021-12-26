package com.bleiny.communities.adapters.inbound.resources;

import com.bleiny.communities.adapters.inbound.dtos.RoomDTO;
import com.bleiny.communities.application.domain.Room;
import com.bleiny.communities.application.exceptions.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/v1/room")
@Api(value = "Operations for room", tags = "ROOM")
public interface RoomResource {

    @PostMapping
    @ApiOperation(value = "Create room", response = Room.class)
    public ResponseEntity<Room> postRoom(@RequestBody @Valid RoomDTO dto) throws ApiException;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get rooms", response = Room.class)
    public ResponseEntity<List<Room>> getRooms(@PathVariable Long id) throws ApiException;
}
