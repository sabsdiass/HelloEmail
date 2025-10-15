package helloemail.application;

import helloemail.domain.services.SimpleEmailService;
import org.springframework.stereotype.Component;

@Component
public class SimpleEmailApp {
    SimpleEmailService service;

    public SimpleEmailApp(SimpleEmailService service) {
        this.service = service;
    }

    public void sendEmail(String to, String subject, String body) {
        service.sendEmail(to, subject, body);
    }
}
