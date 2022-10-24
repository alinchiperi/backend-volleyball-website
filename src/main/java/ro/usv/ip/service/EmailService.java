package ro.usv.ip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import ro.usv.ip.repository.SubscriberRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final ITemplateEngine templateEngine;

    public void sendFakeEmail(String email){
        String link = "http://localhost:3000/";
        String contextTitle = "CSM Suceava este cea mai tare echipa";
        String description= "CSM Suceava a castigt cupa mondiala la volei ";
        String subject="CSM CAMPIOANA";
        try {
            sendEmailWithDetails(link,contextTitle,description, "template",email,subject);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendEmailWithDetails(String link, String contextTitle, String description ,String template, String sendTo, String subject) throws MessagingException {
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
}
