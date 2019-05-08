package pl.wilk.cinematickets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.wilk.cinematickets.model.RoomEntity;
import pl.wilk.cinematickets.repository.RoomRepository;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public void addRoom(RoomEntity room){
        roomRepository.save(room);
    }

    public void editRoom(RoomEntity newRoom, Long id){
        roomRepository.findById(id)
                .ifPresentOrElse(oldRoom -> {
                            if(newRoom.getName() != null)
                                oldRoom.setName(newRoom.getName());
                            roomRepository.save(oldRoom);
                        },
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No room found - id: "+id));
    }

    public List<RoomEntity> findAllRooms(){
        return roomRepository.findAll();
    }

    public RoomEntity findRoomById(Long id){
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No room found - id: "+id));
    }
}
