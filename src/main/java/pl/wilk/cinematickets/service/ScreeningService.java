package pl.wilk.cinematickets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                        },
                        () -> new IllegalArgumentException("No screening found - id: "+id));
    }

    public List<ScreeningEntity> findAllScreenings(){
        return screeningRepository.findAll();
    }

    public List<ScreeningEntity> findAllScreeningsBetween(LocalDateTime start, LocalDateTime end){
        return screeningRepository.findScreeningEntitiesByStartingTimeBetweenOrderByStartingTime(start, end);
    }

    public List<Integer> findAvailableSeatsOfScreening(Long id){
        ScreeningEntity screening = screeningRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No screening found - id: "+id));

        return screening.getSeats().stream()
                .filter(seat -> seat.getReservation() == null)
                .map(seat -> seat.getNumber())
                .collect(Collectors.toList());
    }

}
