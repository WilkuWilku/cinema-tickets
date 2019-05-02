package pl.wilk.cinematickets.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class MovieEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

}
