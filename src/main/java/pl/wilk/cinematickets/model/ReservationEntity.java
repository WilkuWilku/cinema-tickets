package pl.wilk.cinematickets.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ReservationEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private String ownersFirstName;

    @Column
    @NotNull
    private String ownersLastName;

    @Column
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    @ManyToOne
    private ScreeningEntity screening;

}
