package pl.wilk.cinematickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilk.cinematickets.model.ReservationEntity;
import pl.wilk.cinematickets.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity newReservation(@RequestBody ReservationEntity reservation){
        reservationService.addReservation(reservation);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public List<ReservationEntity> listOfReservations(){
        return reservationService.findAllReservations();
    }

    @PutMapping("/{id}")
    public ResponseEntity editReservation(@RequestBody ReservationEntity newReservation, @PathVariable Long id){
        reservationService.editReservation(newReservation, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
