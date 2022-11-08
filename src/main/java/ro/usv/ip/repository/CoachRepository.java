package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.ip.model.Coach;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
}
