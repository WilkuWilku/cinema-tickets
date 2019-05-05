package pl.wilk.cinematickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import pl.wilk.cinematickets.dto.TicketTypeDto;
import pl.wilk.cinematickets.model.TicketTypeEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class TicketTypeMapper {

    public abstract TicketTypeEntity toTicketTypeEntity(TicketTypeDto dto);

    public abstract TicketTypeDto toTicketTypeDto(TicketTypeEntity entity);
}
