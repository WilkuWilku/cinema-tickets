package pl.wilk.cinematickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilk.cinematickets.dto.ScreeningDto;
import pl.wilk.cinematickets.mapper.ScreeningMapper;
import pl.wilk.cinematickets.model.ScreeningEntity;
import pl.wilk.cinematickets.service.ScreeningService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {
    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private ScreeningMapper mapper;

    @PostMapping
    public ResponseEntity newScreening(@RequestBody ScreeningDto screeningDto){
        screeningService.addScreening(mapper.toScreeningEntity(screeningDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<ScreeningDto> listOfScreenings(){
        return screeningService.findAllScreenings().stream()
                .map(screeningEntity -> mapper.toScreeningDto(screeningEntity))
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ScreeningDto> listOfScreeningsBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end){
        return screeningService.findAllScreeningsBetween(start, end).stream()
                .map(screeningEntity -> mapper.toScreeningDto(screeningEntity))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/seats")
    public List<Integer> listOfAvailableSeats(@PathVariable Long id){
        return screeningService.findAvailableSeatsOfScreening(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity editScreening(@RequestBody ScreeningDto newScreeningDto, @PathVariable Long id){
        screeningService.editScreening(mapper.toScreeningEntity(newScreeningDto), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
