package helloemail.multiplerecipients.domain.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

    public class MultipleRecipientsService {
    private final JavaMailSender mailSender;

        @Value("${spring.mail.username}")
        private String fromAddress;

        public MultipleRecipientsService(JavaMailSender mailSender) {
            this.mailSender = mailSender;
        }

        public void sendEmail(String[] to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAddress);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        mailSender.send(msg);
    }

}
//poner el metodo que tiene el email service