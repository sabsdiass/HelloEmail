package helloemail.controller;

import helloemail.infrastructure.common.requests.EmailRequest;
import helloemail.domain.services.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail/api")
public class HelloEmailController {

    private final EmailService emailService;

    public HelloEmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    // TODO: Aplica la misma estructura que con el SimpleSendEmail 😉

    @PostMapping("/sendToMultipleRecipients")
    public String sendToMultipleRecipients(@RequestBody EmailRequest request) {
        String[] recipients = request.getTo().split("\\s*,\\s*"); // separa por vírgula e ignora espaços
        emailService.sendToMultipleRecipients(recipients, request.getSubject(), request.getBody());
        return "OK: múltiplos destinatários";
    }

    @PostMapping("/sendHTMLMessage")
    public String sendHTMLMessage(@RequestBody EmailRequest request) {
        String[] recipients = request.getTo().split("\\s*,\\s*");
        emailService.sendHTMLMessage(recipients, request.getSubject(), request.getBody());
        return "OK: HTML mail enviado";
    }

    @PostMapping("/sendWithAttachment")
    public void sendWithAttachment(@RequestBody EmailRequest request)
            throws jakarta.mail.MessagingException, java.io.IOException {

        String htmlContent = "<h1>This is a NEW test Spring Boot email</h1>"
                + "<p>It can contain <strong>HTML</strong> content.</p>";

        emailService.sendWithAttachment(
                request.getTo(),
                request.getSubject(),
                htmlContent
        );
    }

}
