package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.ip.model.Sponsor;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
}
