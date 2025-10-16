package helloemail.htmlmessage.application;

import helloemail.htmlmessage.domain.services.HTMLMessageService;
import org.springframework.stereotype.Component;

@Component
public class HTMLMessageApp {
    HTMLMessageService service;

    public HTMLMessageApp(HTMLMessageService service) {
        this.service = service;
    }

    public void sendHTMLMessage(String[] to, String subject, String htmlBody) {
        service.sendHTMLMessage(to, subject, htmlBody);
    }
}