package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.usv.ip.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
