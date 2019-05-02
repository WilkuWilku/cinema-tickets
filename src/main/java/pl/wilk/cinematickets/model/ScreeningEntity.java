package pl.wilk.cinematickets.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "screenings")
public class ScreeningEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime startingTime;

    @ManyToOne
    private MovieEntity movie;

    @ManyToOne
    private RoomEntity room;

    @ElementCollection
    @CollectionTable(name="seats", joinColumns = @JoinColumn(name = "screening_id"))
    @Column
    private List<Seat> seats;

}
