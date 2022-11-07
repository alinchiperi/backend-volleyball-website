package ro.usv.ip.Service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.usv.ip.service.EmailService;

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTests {

    @Mock
    EmailService emailService;

    @InjectMocks
    EmailServiceTests emailServiceTests;

    @Test
    void itShouldSendEmailToSubscriber(){

        String subscriber ="chiperialin@yahoo.com";

        doNothing().when(emailService).sendFakeEmail(subscriber);

        // TODO: 03.11.2022  

    }
}
