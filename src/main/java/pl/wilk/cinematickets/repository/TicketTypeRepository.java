package pl.wilk.cinematickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wilk.cinematickets.model.TicketTypeEntity;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketTypeEntity, Long> {
}
