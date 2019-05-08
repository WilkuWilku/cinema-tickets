package pl.wilk.cinematickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wilk.cinematickets.dto.ReservationDto;
import pl.wilk.cinematickets.model.ReservationEntity;
import pl.wilk.cinematickets.service.ReservationService;
import pl.wilk.cinematickets.service.ScreeningService;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ReservationMapper {

    @Autowired
    protected ScreeningService screeningService;

    @Autowired
    protected ReservationService reservationService;

    @Mapping(target = "screening", expression = "java(screeningService.findScreeningById(dto.getScreeningId()))" )
    public abstract ReservationEntity toReservationEntity(ReservationDto dto);

    @Mapping(target = "screeningId", source = "screening.id")
    @Mapping(target = "seats", expression = "java(reservationService.findSeatsOfReservationId(entity.getId()))")
    @Mapping(target = "ticketData", ignore = true)
    public abstract ReservationDto toReservationDto(ReservationEntity entity);

}
