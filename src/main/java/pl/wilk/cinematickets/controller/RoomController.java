package pl.wilk.cinematickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilk.cinematickets.model.RoomEntity;
import pl.wilk.cinematickets.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity newRoom(@RequestBody RoomEntity room){
        roomService.addRoom(room);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public List<RoomEntity> listOfRooms(){
        return roomService.findAllRooms();
    }

    @PutMapping("/{id}")
    public ResponseEntity editRoom(@RequestBody RoomEntity newRoom, @PathVariable Long id){
        roomService.editRoom(newRoom, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
