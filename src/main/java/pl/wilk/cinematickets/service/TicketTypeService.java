package pl.wilk.cinematickets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wilk.cinematickets.model.TicketTypeEntity;
import pl.wilk.cinematickets.repository.TicketTypeRepository;

import java.util.List;
import java.util.Map;

@Service
public class TicketTypeService {

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    public void addTicketType(TicketTypeEntity movie){
        ticketTypeRepository.save(movie);
    }

    public void editTicketType(TicketTypeEntity newTicketType, Long id){
        ticketTypeRepository.findById(id)
                .ifPresentOrElse(oldTicketType ->{
                    if(newTicketType.getName() != null)
                        oldTicketType.setName(newTicketType.getName());
                    if(newTicketType.getPrice() != null)
                        oldTicketType.setPrice(newTicketType.getPrice());
                    ticketTypeRepository.save(oldTicketType);
                                },
                        () -> new IllegalArgumentException("No ticket type found - id: "+id));
    }

    public List<TicketTypeEntity> findAllTicketTypes(){
        return ticketTypeRepository.findAll();
    }

    public void deleteTicketType(Long id){
        ticketTypeRepository.deleteById(id);
    }

    public Long calculateTotalAmount(Map<Long, Integer> ticketData){
        //return ticketData.entrySet().stream()
        //        .map(entry -> entry.getKey()*entry.getValue())
        //        .reduce(0, (sum1, sum2) -> sum1+sum2);

        return ticketData.entrySet().stream()
                .mapToLong(entry ->
                        ticketTypeRepository.findById(entry.getKey())
                                .orElseThrow(() -> new IllegalArgumentException("No ticket type found with id: "+entry.getKey()))
                        .getPrice()*entry.getValue())
                .sum();

    }
}
