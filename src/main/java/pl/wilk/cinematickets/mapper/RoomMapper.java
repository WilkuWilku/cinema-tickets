package pl.wilk.cinematickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import pl.wilk.cinematickets.dto.RoomDto;
import pl.wilk.cinematickets.model.RoomEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class RoomMapper {

    public abstract RoomEntity toRoomEntity(RoomDto dto);

    public abstract RoomDto toRoomDto(RoomEntity entity);
}
