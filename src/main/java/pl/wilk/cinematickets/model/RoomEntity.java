package pl.wilk.cinematickets.model;

import javax.persistence.*;

@Entity
public class RoomEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer maxSeats;
}
