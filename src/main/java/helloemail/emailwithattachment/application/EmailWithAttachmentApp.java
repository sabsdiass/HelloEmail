package helloemail.emailwithattachment.application;

import helloemail.emailwithattachment.domain.services.EmailWithAttachmentService;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import java.io.IOException;

@Component
public class EmailWithAttachmentApp {
    EmailWithAttachmentService service;

    public EmailWithAttachmentApp(EmailWithAttachmentService service) {
        this.service = service;
    }

    public void sendWithAttachment(String to, String subject, String htmlContent)
            throws MessagingException, IOException {
        service.sendWithAttachment(to, subject, htmlContent);
    }
}