package pl.wilk.cinematickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wilk.cinematickets.model.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
