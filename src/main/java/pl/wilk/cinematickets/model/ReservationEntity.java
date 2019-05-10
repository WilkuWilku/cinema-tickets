package pl.wilk.cinematickets.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class ReservationEntity {
    private static final int EXPIRES_AFTER_HOURS = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column
    @Builder.Default
    private LocalDateTime expires = LocalDateTime.now().plusHours(EXPIRES_AFTER_HOURS);

    @ManyToOne
    private ScreeningEntity screening;

}
