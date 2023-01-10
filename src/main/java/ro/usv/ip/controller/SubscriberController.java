package ro.usv.ip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.usv.ip.service.SubscriberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriber")
public class SubscriberController {

    private final SubscriberService service;

    @PostMapping("/add")
    public ResponseEntity<String> addSubscriber(@RequestParam String email){
        return ResponseEntity.ok(service.addSubscriber(email));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteSubscriber(@RequestParam String email){
        return ResponseEntity.ok(service.deleteSubscriber(email));
    }
}
