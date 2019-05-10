package pl.wilk.cinematickets;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.wilk.cinematickets.model.*;
import pl.wilk.cinematickets.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Autowired
    private ReservationRepository reservationRepository;


    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

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
                .startingTime(LocalDateTime.of(2019, 5, 25, 15, 0, 0, 0))
                .seats(IntStream.range(1, room1.getMaxSeats()+1)
                        .mapToObj(index -> Seat.builder().number(index).build())
                        .collect(Collectors.toList()))
                .build();

        ScreeningEntity screening2 = ScreeningEntity.builder()
                .movie(movie2)
                .room(room1)
                .startingTime(LocalDateTime.of(2019, 5, 25, 20, 45, 0, 0))
                .seats(IntStream.range(1, room1.getMaxSeats()+1)
                        .mapToObj(index -> Seat.builder().number(index).build())
                        .collect(Collectors.toList()))
                .build();

        ScreeningEntity screening3 = ScreeningEntity.builder()
                .movie(movie1)
                .room(room2)
                .startingTime(LocalDateTime.of(2019, 5, 26, 16, 20, 0, 0))
                .seats(IntStream.range(1, room2.getMaxSeats()+1)
                        .mapToObj(index -> Seat.builder().number(index).build())
                        .collect(Collectors.toList()))
                .build();

        ScreeningEntity screening4 = ScreeningEntity.builder()
                .movie(movie3)
                .room(room2)
                .startingTime(LocalDateTime.of(2019, 5, 26, 21, 45, 0, 0))
                .seats(IntStream.range(1, room2.getMaxSeats()+1)
                        .mapToObj(index -> Seat.builder().number(index).build())
                        .collect(Collectors.toList()))
                .build();

        ScreeningEntity screening5 = ScreeningEntity.builder()
                .movie(movie2)
                .room(room3)
                .startingTime(LocalDateTime.of(2019, 5, 26, 15, 15, 0, 0))
                .seats(IntStream.range(1, room3.getMaxSeats()+1)
                        .mapToObj(index -> Seat.builder().number(index).build())
                        .collect(Collectors.toList()))
                .build();

        ScreeningEntity screening6 = ScreeningEntity.builder()
                .movie(movie1)
                .room(room3)
                .startingTime(LocalDateTime.of(2019, 5, 28, 21, 55, 0, 0))
                .seats(IntStream.range(1, room3.getMaxSeats()+1)
                        .mapToObj(index -> Seat.builder().number(index).build())
                        .collect(Collectors.toList()))
                .build();

        screeningRepository.saveAll(List.of(screening1, screening2, screening3, screening4, screening5, screening6));


        /* Insert Reservations */
        ReservationEntity reservation1 = ReservationEntity.builder()
                .ownersFirstName("Jan")
                .ownersLastName("Kowalski")
                .screening(screening3)
                .build();

        reservationRepository.save(reservation1);
        screening3.getSeats().stream()
                .filter(seat -> Set.of(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14).contains(seat.getNumber()))
                .forEach(seat -> seat.setReservation(reservation1));
        screeningRepository.save(screening3);

    }
}
