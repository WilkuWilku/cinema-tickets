package pl.wilk.cinematickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilk.cinematickets.dto.RoomDto;
import pl.wilk.cinematickets.mapper.RoomMapper;
import pl.wilk.cinematickets.model.RoomEntity;
import pl.wilk.cinematickets.service.RoomService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomMapper mapper;

    @PostMapping
    public ResponseEntity newRoom(@RequestBody RoomDto roomDto){
        roomService.addRoom(mapper.toRoomEntity(roomDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public List<RoomDto> listOfRooms(){
        return roomService.findAllRooms().stream()
                .map(roomEntity -> mapper.toRoomDto(roomEntity))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity editRoom(@RequestBody RoomDto newRoomDto, @PathVariable Long id){
        roomService.editRoom(mapper.toRoomEntity(newRoomDto), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
