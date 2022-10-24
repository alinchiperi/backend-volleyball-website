package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.usv.ip.model.Subscriber;


public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

}
