package pl.wilk.cinematickets.service;

import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wilk.cinematickets.model.ReservationEntity;
import pl.wilk.cinematickets.model.ScreeningEntity;
import pl.wilk.cinematickets.model.Seat;
import pl.wilk.cinematickets.repository.ReservationRepository;
import pl.wilk.cinematickets.repository.ScreeningRepository;

import java.util.*;
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

    private void validateSeatsDistance(List<Integer> seatNumbers, List<Seat> screeningSeats){
        /* Max number of seat in room */
        Integer maxNumber = screeningSeats.stream()
                .max(Comparator.comparing(Seat::getNumber))
                .map(seat->seat.getNumber())
                .get();

        /* Deep copy of screening seats to apply changes */
        List<Seat> testSeats = screeningSeats.stream()
                .map(seat -> Seat.builder()
                        .number(seat.getNumber())
                        .reservation(seat.getReservation())
                        .build())
                .collect(Collectors.toList());

        /* Mark seats that would be reserved */
        seatNumbers.forEach(seatNumber ->
                testSeats.stream()
                        .filter(seat -> seat.getNumber().equals(seatNumber))
                        .findFirst()
                        .get()
                        .setReservation(ReservationEntity.builder().id(-1L).build()));

        /* Check if there is no gap between two reserved seats in entire room */
        testSeats.forEach(testSeat -> {
            /* Last seat - no seats on right */
            if(testSeat.getNumber().equals(maxNumber))
                return;

            /* One before last seat - throw exception if seat on right is not reserved */
            if(testSeat.getNumber().equals(maxNumber-1)) {
                Seat onRight = testSeats.stream()
                        .filter(s -> s.getNumber().equals(testSeat.getNumber()+1))
                                                    .findFirst().get();

                if(testSeat.getReservation() != null && onRight.getReservation() == null)
                    throw new IllegalArgumentException("There cannot be a single seat left over between two already reserved seats: "+testSeat.getNumber());

            } else {
                /* Check two seats on right */
                Seat firstOnRight = testSeats.stream()
                        .filter(s -> s.getNumber().equals(testSeat.getNumber()+1))
                        .findFirst().get();
                Seat secondOnRight = testSeats.stream()
                        .filter(s -> s.getNumber().equals(testSeat.getNumber()+2))
                        .findFirst().get();

                if(testSeat.getReservation() != null && firstOnRight.getReservation() == null && secondOnRight.getReservation() != null)
                    throw new IllegalArgumentException("There cannot be a single seat left over between two already reserved seats: "+testSeat.getNumber());
            }

        });
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
        validateSeatsDistance(seatNumbers, screeningSeats);

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
