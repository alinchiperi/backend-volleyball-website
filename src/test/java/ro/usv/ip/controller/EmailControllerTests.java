package ro.usv.ip.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.usv.ip.dto.ContactDto;
import ro.usv.ip.service.EmailService;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailControllerTests {

    @InjectMocks
    private EmailController emailController;

    @Mock
    private EmailService emailService;

    @Test
    public void testSendContactMessage() {
        // Prepare data
        ContactDto contact = ContactDto.builder().build();
        contact.setName("John");
        contact.setMessage("Hello");
        contact.setEmail("john@example.com");
        contact.setCheckboxCopy(true);

        emailController.sendContactMessage(contact);

        verify(emailService).sendContactMessage(contact);
    }
}

