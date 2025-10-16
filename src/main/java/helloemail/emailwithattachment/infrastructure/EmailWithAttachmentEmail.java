package helloemail.emailwithattachment.infrastructure;

import helloemail.common.infrastructure.requests.EmailRequest;
import helloemail.emailwithattachment.application.EmailWithAttachmentApp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail/api")
public class EmailWithAttachmentEmail {

    private final EmailWithAttachmentApp emailwithattachmentapp;

    public EmailWithAttachmentEmail(
            EmailWithAttachmentApp emailwithattachmentapp //el objeto siempre en minusculas
    ) {
        this.emailwithattachmentapp = emailwithattachmentapp;
    }

    @PostMapping("/sendWithAttachment")
    public void sendWithAttachment(@RequestBody EmailRequest request)
            throws jakarta.mail.MessagingException, java.io.IOException {

        String htmlContent = "<h1>This is a NEW test Spring Boot email</h1>"
                + "<p>It can contain <strong>HTML</strong> content.</p>";

        emailwithattachmentapp.sendWithAttachment(
                request.getTo(),
                request.getSubject(),
                htmlContent
        );
    }
}