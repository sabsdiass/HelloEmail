package helloemail.domain.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    @Value("${spring.mail.username}")
    private String fromAddress; // o Spring injeta – ignore o warning do IntelliJ

    private final JavaMailSender mailSender;

    public SimpleEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // --- texto simples (1 destinatário)
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAddress);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        mailSender.send(msg);
    }
}
