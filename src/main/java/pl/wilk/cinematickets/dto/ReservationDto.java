package pl.wilk.cinematickets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private String ownersFirstName;
    private String ownersLastName;
    private Long screeningId;
}
