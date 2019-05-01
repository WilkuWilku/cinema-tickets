package pl.wilk.cinematickets.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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

}
