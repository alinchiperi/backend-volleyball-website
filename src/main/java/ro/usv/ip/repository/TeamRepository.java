package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.ip.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
