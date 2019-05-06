package pl.wilk.cinematickets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private String ownersFirstName;
    private String ownersLastName;
    private List<Integer> seats;
    private Long screeningId;
    private Map<Long, Integer> ticketData;
}
