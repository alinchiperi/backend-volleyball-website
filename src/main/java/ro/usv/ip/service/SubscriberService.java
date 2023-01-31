package ro.usv.ip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.usv.ip.model.Subscriber;
import ro.usv.ip.repository.SubscriberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final SubscriberRepository subscriberRepository;

    /**
     * add a subscriber to database
     * @param email subscriber email
     * @return  a message
     */
    public String addSubscriber(String email) {
        Optional<Subscriber> subscriber = subscriberRepository.findByEmail(email);
        if (subscriber.isPresent()) {
            throw new RuntimeException("Email already exists");
        } else {
            subscriberRepository.save(new Subscriber(email));
            return "Subscriber salvat " + email;
        }
    }

    /**
     * delete a subscriber
     * @param email subscriber email
     * @return a message
     */
    public String deleteSubscriber(String email) {
        Optional<Subscriber> subscriber = subscriberRepository.findByEmail(email);
        if(subscriber.isPresent()){
            subscriberRepository.delete(subscriber.get());
            return subscriber.get().getEmail()+ " unsubscribed";
        }
        else {
            return "email inexistent";
        }
    }
}
