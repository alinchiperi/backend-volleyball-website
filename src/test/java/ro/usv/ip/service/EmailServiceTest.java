package ro.usv.ip.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import ro.usv.ip.dto.ContactDto;
import ro.usv.ip.model.Post;
import ro.usv.ip.model.Subscriber;
import ro.usv.ip.repository.PostRepository;
import ro.usv.ip.repository.SubscriberRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    PostRepository postRepository;
    @Mock
    SubscriberRepository subscriberRepository;
    @Mock
    private ITemplateEngine templateEngine;
    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    EmailService emailService;


    @Test
    public void sendNewsToSubscriberTest() {
        //given
        List<Subscriber> subscribers = Arrays.asList(new Subscriber("subscriber1@example.com"), new Subscriber("subscriber2@example.com"));
        List<Post> posts = new ArrayList<>();
        Post post = new Post();
        post.setTitle("Test Post");
        post.setUnderTitle("Under Title");
        post.setContent("Test content...");
        posts.add(post);

        // Mock behavior
        MimeMessage mockMimeMessage = mock(MimeMessage.class);
        when(subscriberRepository.findAll()).thenReturn(subscribers);
        when(javaMailSender.createMimeMessage()).thenReturn(mockMimeMessage);
        when(postRepository.findAllOrderByCreatedOnAsc()).thenReturn(posts);
        when(templateEngine.process(eq("template"), any(Context.class))).thenReturn("Processed Template");

        emailService.sendNewsToSubscriber();

        // Verify interactions
        //then
        verify(javaMailSender, times(2)).send(any(MimeMessage.class));
    }

    @Test
    public void testSendContactMessage() {
        // Prepare data
        ContactDto contact = ContactDto.builder().name("John")
                .message("Hello")
                .email("john@example.com")
                .checkboxCopy(false)
                .build();

        // Call the method
        emailService.sendContactMessage(contact);

        // Verify interactions
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender, times(1)).send(captor.capture()); // Verify that emails were sent

        List<SimpleMailMessage> sentMessages = captor.getAllValues();
        assertEquals("csm.volei.suceava@gmail.com", Objects.requireNonNull(sentMessages.get(0).getTo())[0]);

    }
}
