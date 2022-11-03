package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.ip.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
