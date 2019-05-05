package pl.wilk.cinematickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilk.cinematickets.dto.ReservationDto;
import pl.wilk.cinematickets.mapper.ReservationMapper;
import pl.wilk.cinematickets.model.ReservationEntity;
import pl.wilk.cinematickets.service.ReservationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationMapper mapper;

    @PostMapping
    public ResponseEntity newReservation(@RequestBody ReservationDto reservationDto){
        reservationService.addReservation(mapper.toReservationEntity(reservationDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public List<ReservationDto> listOfReservations(){
        return reservationService.findAllReservations().stream()
                .map(reservationEntity -> mapper.toReservationDto(reservationEntity))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity editReservation(@RequestBody ReservationDto newReservationDto, @PathVariable Long id){
        reservationService.editReservation(mapper.toReservationEntity(newReservationDto), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
