package pl.wilk.cinematickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilk.cinematickets.dto.TicketTypeDto;
import pl.wilk.cinematickets.mapper.TicketTypeMapper;
import pl.wilk.cinematickets.model.TicketTypeEntity;
import pl.wilk.cinematickets.service.TicketTypeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ticket-types")
public class TicketTypeController {
    @Autowired
    private TicketTypeService ticketTypeService;

    @Autowired
    private TicketTypeMapper mapper;

    @PostMapping
    public ResponseEntity newTicketType(@RequestBody TicketTypeDto ticketTypeDto) {
        ticketTypeService.addTicketType(mapper.toTicketTypeEntity(ticketTypeDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public List<TicketTypeDto> listOfTicketTypes(){
        return ticketTypeService.findAllTicketTypes().stream()
                .map(ticketTypeEntity -> mapper.toTicketTypeDto(ticketTypeEntity))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity editTicketType(@RequestBody TicketTypeDto newTicketTypeDto, @PathVariable Long id){
        ticketTypeService.editTicketType(mapper.toTicketTypeEntity(newTicketTypeDto), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTicketType(@PathVariable Long id){
        ticketTypeService.deleteTicketType(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
