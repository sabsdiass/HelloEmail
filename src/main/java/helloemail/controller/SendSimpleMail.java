package helloemail.controller;

import helloemail.request.EmailRequest;
import helloemail.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail/api")
public class SendSimpleMail {

    private final EmailService emailService;

    public SendSimpleMail(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendSimpleMail")
    public String sendSimpleMail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
        return "OK: simple mail enviado";
    }
}
