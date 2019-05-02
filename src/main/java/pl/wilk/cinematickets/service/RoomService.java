package pl.wilk.cinematickets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                .ifPresentOrElse(oldRoom ->
                    oldRoom.setName(newRoom.getName()),
                        () -> new IllegalArgumentException("No room found - id: "+id));
    }

    public List<RoomEntity> findAllRooms(){
        return roomRepository.findAll();
    }
}
