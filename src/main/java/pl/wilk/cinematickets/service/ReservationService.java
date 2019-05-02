package pl.wilk.cinematickets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wilk.cinematickets.model.ReservationEntity;
import pl.wilk.cinematickets.repository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public void addReservation(ReservationEntity reservation){
        reservationRepository.save(reservation);
    }

    public void editReservation(ReservationEntity newReservation, Long id){
        reservationRepository.findById(id)
                .ifPresentOrElse(oldReservation -> {
                    if(newReservation.getOwnersFirstName() != null)
                        oldReservation.setOwnersFirstName(newReservation.getOwnersFirstName());
                    if(newReservation.getOwnersLastName() != null)
                        oldReservation.setOwnersLastName(newReservation.getOwnersLastName());
                        },
                        () -> new IllegalArgumentException("No reservation found - id: "+id));
    }

    public List<ReservationEntity> findAllReservations(){
        return reservationRepository.findAll();
    }
}
