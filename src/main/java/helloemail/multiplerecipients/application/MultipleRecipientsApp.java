package helloemail.multiplerecipients.application;

import helloemail.multiplerecipients.domain.services.MultipleRecipientsService;
import org.springframework.stereotype.Component;

@Component
public class MultipleRecipientsApp {
    MultipleRecipientsService service;

    public MultipleRecipientsApp(MultipleRecipientsService service) {
        this.service = service;
    }

    public void sendEmail(String[] to, String subject, String body) {
        service.sendEmail(to, subject, body);
    }
}