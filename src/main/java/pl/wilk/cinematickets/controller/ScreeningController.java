package pl.wilk.cinematickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilk.cinematickets.model.ScreeningEntity;
import pl.wilk.cinematickets.service.ScreeningService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {
    @Autowired
    private ScreeningService screeningService;

    @PostMapping
    public ResponseEntity newScreening(@RequestBody ScreeningEntity screening){
        screeningService.addScreening(screening);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<ScreeningEntity> listOfScreenings(){
        return screeningService.findAllScreenings();
    }

    @GetMapping
    public List<ScreeningEntity> listOfScreeningsBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end){
        return screeningService.findAllScreeningsBetween(start, end);
    }

    @GetMapping("/{id}/seats")
    public List<Integer> listOfAvailableSeats(@PathVariable Long id){
        return screeningService.findAvailableSeatsOfScreening(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity editScreening(@RequestBody ScreeningEntity newScreening, @PathVariable Long id){
        screeningService.editScreening(newScreening, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
