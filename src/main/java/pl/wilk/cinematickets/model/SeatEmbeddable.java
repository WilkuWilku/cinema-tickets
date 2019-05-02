package pl.wilk.cinematickets.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SeatEmbeddable {
    @Column
    private Integer number;

    @Column
    private Boolean isReserved;
}
