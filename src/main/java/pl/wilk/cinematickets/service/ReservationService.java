package pl.wilk.cinematickets.service;

import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wilk.cinematickets.model.ReservationEntity;
import pl.wilk.cinematickets.model.ScreeningEntity;
import pl.wilk.cinematickets.model.Seat;
import pl.wilk.cinematickets.repository.ReservationRepository;
import pl.wilk.cinematickets.repository.ScreeningRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    private void validateTicketsNumber(List<Integer> seatNumbers, Map<Long, Integer> ticketData){
        Integer ticketCount = ticketData.entrySet().stream()
                .mapToInt(entry -> entry.getValue())
                .sum();
        if(!ticketCount.equals(seatNumbers.size()))
            throw new IllegalArgumentException("Number of tickets differs from number of seats to reserve");
    }

    private void validateSeatsExistence(List<Integer> seatNumbers, List<Integer> screeningSeatNumbers){
        if(seatNumbers.stream()
                .anyMatch(seatNumber -> !screeningSeatNumbers.contains(seatNumber)))
            throw new IllegalArgumentException("List of seats contains seat numbers that do not exist in room");
    }

    private void validateSeatsAvailability(List<Seat> screeningSeats, List<Integer> seatNumbers){
        if(screeningSeats.stream()
                .filter(seat -> seatNumbers.contains(seat.getNumber()))
                .anyMatch(seat -> seat.getReservation() != null))
            throw new IllegalArgumentException("List of seats to reserve contains already reserved seats");
    }

    public ReservationEntity addReservation(ReservationEntity reservation, List<Integer> seatNumbers,
                                            Map<Long, Integer> ticketData){
        ScreeningEntity screeningEntity = reservation.getScreening();
        List<Seat> screeningSeats = screeningEntity.getSeats();
        List<Integer> screeningSeatNumbers = screeningSeats.stream()
                .map(seat -> seat.getNumber())
                .collect(Collectors.toList());

        validateTicketsNumber(seatNumbers, ticketData);
        validateSeatsExistence(seatNumbers, screeningSeatNumbers);
        validateSeatsAvailability(screeningSeats, seatNumbers);

        ReservationEntity savedReservation = reservationRepository.save(reservation);
        screeningSeats.stream()
                .filter(seat -> seatNumbers.contains(seat.getNumber()))
                .forEach(seat -> seat.setReservation(savedReservation));
        screeningRepository.save(screeningEntity);

        return savedReservation;
    }

    public void editReservation(ReservationEntity newReservation, Long id){
        reservationRepository.findById(id)
                .ifPresentOrElse(oldReservation -> {
                    if(newReservation.getOwnersFirstName() != null)
                        oldReservation.setOwnersFirstName(newReservation.getOwnersFirstName());
                    if(newReservation.getOwnersLastName() != null)
                        oldReservation.setOwnersLastName(newReservation.getOwnersLastName());
                    reservationRepository.save(oldReservation);
                        },
                        () -> new IllegalArgumentException("No reservation found - id: "+id));
    }

    public List<ReservationEntity> findAllReservations(){
        return reservationRepository.findAll();
    }

    public List<Integer> findSeatsOfReservationId(Long id){
        ReservationEntity reservationEntity = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalReceiveException("No reservation found - id: "+id));
        return reservationEntity.getScreening().getSeats().stream()
                .filter(seat -> seat.getReservation() != null)
                .filter(seat -> seat.getReservation().getId().equals(id))
                .map(seat -> seat.getNumber())
                .collect(Collectors.toList());
    }

}
