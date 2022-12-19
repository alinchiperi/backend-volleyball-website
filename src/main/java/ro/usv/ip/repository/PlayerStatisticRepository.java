package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.usv.ip.model.PlayerStatistic;

public interface PlayerStatisticRepository extends JpaRepository<PlayerStatistic, Long> {
    PlayerStatistic findByPlayerId(Long playerId);
}
