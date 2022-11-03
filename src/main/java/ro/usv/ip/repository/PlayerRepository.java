package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.ip.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
