package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.usv.ip.model.PlayerStatistic;

import java.util.List;

public interface PlayerStatisticRepository extends JpaRepository<PlayerStatistic, Long> {
    List<PlayerStatistic> findByPlayerIdOrderBySeasonStartDesc(Long playerId);
}
