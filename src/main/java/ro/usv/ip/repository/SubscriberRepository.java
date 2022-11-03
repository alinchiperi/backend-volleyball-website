package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.ip.model.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

}
