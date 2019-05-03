package pl.wilk.cinematickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilk.cinematickets.model.TicketTypeEntity;
import pl.wilk.cinematickets.service.TicketTypeService;

import java.util.List;

@RestController
@RequestMapping("/ticket-types")
public class TicketTypeController {
    @Autowired
    private TicketTypeService ticketTypeService;

    @PostMapping
    public ResponseEntity newTicketType(@RequestBody TicketTypeEntity ticketType) {
        ticketTypeService.addTicketType(ticketType);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public List<TicketTypeEntity> listOfTicketTypes(){
        return ticketTypeService.findAllTicketTypes();
    }

    @PutMapping("/{id}")
    public ResponseEntity editTicketType(@RequestBody TicketTypeEntity newTicketType, @PathVariable Long id){
        ticketTypeService.editTicketType(newTicketType, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTicketType(@PathVariable Long id){
        ticketTypeService.deleteTicketType(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
