package pl.wilk.cinematickets.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Seat {

    @Column
    private Integer number;

    @ManyToOne
    private ReservationEntity reservation;


}
