package pl.wilk.cinematickets.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "ticketTypes")
public class TicketTypeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Long price;
}
