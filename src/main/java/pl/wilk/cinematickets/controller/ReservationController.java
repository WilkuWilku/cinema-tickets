package pl.wilk.cinematickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilk.cinematickets.dto.ReservationDto;
import pl.wilk.cinematickets.mapper.ReservationMapper;
import pl.wilk.cinematickets.model.ReservationEntity;
import pl.wilk.cinematickets.service.ReservationService;
import pl.wilk.cinematickets.service.TicketTypeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TicketTypeService ticketTypeService;

    @Autowired
    private ReservationMapper mapper;

    @PostMapping
    public ResponseEntity<Map<String, Object>> newReservation(@Valid @RequestBody ReservationDto reservationDto){
        ReservationEntity reservationEntity = reservationService.addReservation(
                mapper.toReservationEntity(reservationDto), reservationDto.getSeats(), reservationDto.getTicketData());
        Long totalAmount = ticketTypeService.calculateTotalAmount(reservationDto.getTicketData());
        return new ResponseEntity<>(Map.of("expires", reservationEntity.getExpires(), "total", totalAmount), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ReservationDto> listOfReservations(){
        return reservationService.findAllReservations().stream()
                .map(reservationEntity -> mapper.toReservationDto(reservationEntity))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<Integer> getReservation(@PathVariable Long id){
        return reservationService.findSeatsOfReservationId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity editReservation(@Valid @RequestBody ReservationDto newReservationDto, @PathVariable Long id){
        reservationService.editReservation(mapper.toReservationEntity(newReservationDto), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
