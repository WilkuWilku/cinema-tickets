package pl.wilk.cinematickets.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime startingTime;

    @ManyToOne
    @JsonBackReference
    private MovieEntity movie;

    @ManyToOne
    @JsonBackReference
    private RoomEntity room;

    @ElementCollection
    @CollectionTable(name="seats", joinColumns = @JoinColumn(name = "screening_id"))
    @Column
    private List<Seat> seats;

}
