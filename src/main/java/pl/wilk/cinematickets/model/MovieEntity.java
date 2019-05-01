package pl.wilk.cinematickets.model;


import javax.persistence.*;

@Entity
public class MovieEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

}
