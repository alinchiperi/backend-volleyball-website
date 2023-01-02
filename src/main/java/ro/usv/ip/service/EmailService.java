package ro.usv.ip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import ro.usv.ip.model.Post;
import ro.usv.ip.model.Subscriber;
import ro.usv.ip.repository.PostRepository;
import ro.usv.ip.repository.SubscriberRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final ITemplateEngine templateEngine;
    private final SubscriberRepository subscriberRepository;
    private final PostRepository postRepository;

    public void sendFakeEmail(String email) {
        String link = "http://localhost:3000/";
        String contextTitle = "CSM Suceava este cea mai tare echipa";
        String description = "CSM Suceava a castigt cupa mondiala la volei ";
        String subject = "CSM CAMPIOANA";
        try {
            sendEmailWithDetails(link, contextTitle, description, "template", email, subject);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendEmailWithDetails(String link, String contextTitle, String description, String template, String sendTo, String subject) throws MessagingException {
        Context context = new Context();
        context.setVariable("title", contextTitle);
        context.setVariable("link", link);
        context.setVariable("description", description);

        String body = templateEngine.process(template, context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(sendTo);
        helper.setSubject(subject);
        helper.setText(body, true);
        javaMailSender.send(message);
    }

    /**
     * method executed in each monday at 13:30 Bucharest time
     */
    @Scheduled(cron = "0 30 13 * * MON", zone="Europe/Bucharest")
//    @Scheduled(fixedRate = 1000)
    public void sendNewsToSubscriber() {
        List<Subscriber> subscribers = subscriberRepository.findAll();
        List<Post> posts = postRepository.findAllOrderByCreatedOnAsc();

        Post lastPost =posts.get(0);
//        String link = lastPost.getLink();
        String link = "localhost:3030";
        String contextTitle = lastPost.getTitle();
        String description = lastPost.getUnderTitle();

        String subject = "Ultimele noutatiti ";

        try {
            for (Subscriber subs :
                    subscribers) {

            sendEmailWithDetails(link, contextTitle, description, "template", subs.getEmail(), subject);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
