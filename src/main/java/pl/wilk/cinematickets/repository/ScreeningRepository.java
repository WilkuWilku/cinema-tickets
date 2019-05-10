package pl.wilk.cinematickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wilk.cinematickets.model.ScreeningEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<ScreeningEntity, Long> {
    List<ScreeningEntity> findScreeningEntitiesByStartingTimeBetweenOrderByStartingTime
            (LocalDateTime start, LocalDateTime end);

}
