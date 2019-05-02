package pl.wilk.cinematickets;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.wilk.cinematickets.model.*;
import pl.wilk.cinematickets.repository.MovieRepository;
import pl.wilk.cinematickets.repository.RoomRepository;
import pl.wilk.cinematickets.repository.ScreeningRepository;
import pl.wilk.cinematickets.repository.TicketTypeRepository;

import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class CinemaTicketsApplication implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    public static void main(String[] args) {
        SpringApplication.run(CinemaTicketsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /* Insert Movies */
        MovieEntity movie1 = MovieEntity.builder()
                .title("Ania z Zielonego Wzgórza").build();
        MovieEntity movie2 = MovieEntity.builder()
                .title("Szklana Pułapka 7").build();
        MovieEntity movie3 = MovieEntity.builder()
                .title("Piła 10").build();
        movieRepository.saveAll(List.of(movie1, movie2, movie3));


        /* Insert Rooms */
        RoomEntity room1 = RoomEntity.builder()
                .maxSeats(20)
                .name("Sala 1").build();
        RoomEntity room2 = RoomEntity.builder()
                .maxSeats(30)
                .name("Sala 2").build();
        RoomEntity room3 = RoomEntity.builder()
                .maxSeats(15)
                .name("Sala 3").build();
        roomRepository.saveAll(List.of(room1, room2, room3));


        /* Insert TicketTypes */
        TicketTypeEntity ticketType1 = TicketTypeEntity.builder()
                .name("Dorosły")
                .price(2500L).build();
        TicketTypeEntity ticketType2 = TicketTypeEntity.builder()
                .name("Student")
                .price(1800L).build();
        TicketTypeEntity ticketType3 = TicketTypeEntity.builder()
                .name("Dziecko")
                .price(1250L).build();
        ticketTypeRepository.saveAll(List.of(ticketType1, ticketType2, ticketType3));


        /* Insert Screenings */
        ScreeningEntity screening1 = ScreeningEntity.builder()
                .movie(movie1)
                .room(room1)
                .startingTime(LocalDateTime.of(2019, 5, 13, 15, 0, 0, 0))
                .seats(IntStream.range(1, room1.getMaxSeats()+1)
                        .mapToObj(index ->
                            SeatEmbeddable.builder()
                                    .isReserved(false)
                                    .number(index).build()
                        ).collect(Collectors.toList()))
                .build();

        ScreeningEntity screening2 = ScreeningEntity.builder()
                .movie(movie2)
                .room(room1)
                .startingTime(LocalDateTime.of(2019, 5, 13, 20, 45, 0, 0))
                .seats(IntStream.range(1, room1.getMaxSeats()+1)
                        .mapToObj(index ->
                                SeatEmbeddable.builder()
                                        .isReserved(false)
                                        .number(index).build()
                        ).collect(Collectors.toList()))
                .build();

        ScreeningEntity screening3 = ScreeningEntity.builder()
                .movie(movie1)
                .room(room2)
                .startingTime(LocalDateTime.of(2019, 5, 14, 16, 20, 0, 0))
                .seats(IntStream.range(1, room2.getMaxSeats()+1)
                        .mapToObj(index ->
                                SeatEmbeddable.builder()
                                        .isReserved(false)
                                        .number(index).build()
                        ).collect(Collectors.toList()))
                .build();

        ScreeningEntity screening4 = ScreeningEntity.builder()
                .movie(movie3)
                .room(room2)
                .startingTime(LocalDateTime.of(2019, 5, 14, 21, 45, 0, 0))
                .seats(IntStream.range(1, room2.getMaxSeats()+1)
                        .mapToObj(index ->
                                SeatEmbeddable.builder()
                                        .isReserved(false)
                                        .number(index).build()
                        ).collect(Collectors.toList()))
                .build();

        ScreeningEntity screening5 = ScreeningEntity.builder()
                .movie(movie2)
                .room(room3)
                .startingTime(LocalDateTime.of(2019, 5, 14, 15, 15, 0, 0))
                .seats(IntStream.range(1, room3.getMaxSeats()+1)
                        .mapToObj(index ->
                                SeatEmbeddable.builder()
                                        .isReserved(false)
                                        .number(index).build()
                        ).collect(Collectors.toList()))
                .build();

        ScreeningEntity screening6 = ScreeningEntity.builder()
                .movie(movie1)
                .room(room3)
                .startingTime(LocalDateTime.of(2019, 5, 13, 21, 45, 0, 0))
                .seats(IntStream.range(1, room3.getMaxSeats()+1)
                        .mapToObj(index ->
                                SeatEmbeddable.builder()
                                        .isReserved(false)
                                        .number(index).build()
                        ).collect(Collectors.toList()))
                .build();

        screeningRepository.saveAll(List.of(screening1, screening2, screening3, screening4, screening5, screening6));
    }
}
