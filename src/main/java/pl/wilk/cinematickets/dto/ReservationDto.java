package pl.wilk.cinematickets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;

    @Size(min = 3, message = "{reservation.firstname.size}")
    @Pattern(regexp = "[A-Z|ĘÓĄŚŁŻŹĆŃ][A-Za-z|ęóąśłżźćńĘÓĄŚŁŻŹĆŃ]+",
            message = "{reservation.firstname.pattern}")
    private String ownersFirstName;

    @Size(min = 3, message = "{reservation.lastname.size}")
    @Pattern(regexp =
            "[A-Z|ĘÓĄŚŁŻŹĆŃ][A-Za-z|ęóąśłżźćńĘÓĄŚŁŻŹĆŃ]+(-[A-Z|ĘÓĄŚŁŻŹĆŃ][A-Za-z|ęóąśłżźćńĘÓĄŚŁŻŹĆŃ]+)?",
            message = "{reservation.lastname.pattern}")
    private String ownersLastName;

    @NotEmpty(message = "{reservation.seats.notempty}")
    private List<Integer> seats;

    @NotNull(message = "{reservation.screeningId.notnull}")
    private Long screeningId;

    /* object ticketTypeId: numberOfTickets */
    @NotNull(message = "{reservation.ticketdata.notnull}")
    private Map<Long, Integer> ticketData;
}
