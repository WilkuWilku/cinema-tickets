package pl.wilk.cinematickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wilk.cinematickets.dto.ScreeningDto;
import pl.wilk.cinematickets.model.ScreeningEntity;
import pl.wilk.cinematickets.service.MovieService;
import pl.wilk.cinematickets.service.RoomService;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ScreeningMapper {

    @Autowired
    protected MovieService movieService;

    @Autowired
    protected RoomService roomService;

    @Mapping(target = "movie", expression = "java(movieService.findMovieById(dto.getMovieId()))")
    @Mapping(target = "room", expression = "java(roomService.findRoomById(dto.getRoomId()))")
    public abstract ScreeningEntity toScreeningEntity(ScreeningDto dto);

    @Mapping(target = "movieId", source = "movie.id")
    @Mapping(target = "roomId", source = "room.id")
    public abstract ScreeningDto toScreeningDto(ScreeningEntity entity);
}
