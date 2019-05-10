package pl.wilk.cinematickets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.wilk.cinematickets.model.ScreeningEntity;
import pl.wilk.cinematickets.repository.ScreeningRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreeningService {

    @Autowired
    private ScreeningRepository screeningRepository;

    public void addScreening(ScreeningEntity screening){
        screeningRepository.save(screening);
    }

    public void editScreening(ScreeningEntity newScreening, Long id){
        screeningRepository.findById(id)
                .ifPresentOrElse(oldScreening -> {
                    if(newScreening.getMovie() != null)
                        oldScreening.setMovie(newScreening.getMovie());
                    if(newScreening.getRoom() != null)
                        oldScreening.setRoom(newScreening.getRoom());
                    screeningRepository.save(oldScreening);
                        },
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No screening found - id: "+id));
    }

    public List<ScreeningEntity> findAllScreenings(){
        return screeningRepository.findAll();
    }

    public List<ScreeningEntity> findAllScreeningsBetween(LocalDateTime start, LocalDateTime end){
        return screeningRepository.findScreeningEntitiesByStartingTimeBetweenOrderByStartingTime(start, end);
    }

    public List<Integer> findAvailableSeatsOfScreening(Long id){
        ScreeningEntity screening = findScreeningById(id);

        return screening.getSeats().stream()
                .filter(seat -> seat.getReservation() == null)
                .map(seat -> seat.getNumber())
                .collect(Collectors.toList());
    }

    public ScreeningEntity findScreeningById(Long id){
        return screeningRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"No screening found - id: "+id));
    }


}
