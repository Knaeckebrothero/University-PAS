package fra.uas.pas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    //String to, String subject, String text
    public void sendSimpleMessage() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@example.com");
            message.setTo("");
            message.setSubject("TestSpringMail");
            message.setText("This is a test message.");
           emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
            // Handle the exception accordingly
        }
    }
}
