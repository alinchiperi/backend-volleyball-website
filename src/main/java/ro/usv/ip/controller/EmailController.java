package ro.usv.ip.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.usv.ip.dto.ContactDto;
import ro.usv.ip.service.EmailService;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    /**
     * Send email to test for a user
     * @param email
     */
    @GetMapping("/news")
    public void sendNewsLetter(@RequestParam String email){
        emailService.sendFakeEmail(email);
    }

    /**
     * Send a email to a Contact
     * @param contact Contact to sent
     */
    @PostMapping("/contact")
    public void sendContactMessage(@RequestBody ContactDto contact){
        emailService.sendContactMessage(contact);
    }
}
