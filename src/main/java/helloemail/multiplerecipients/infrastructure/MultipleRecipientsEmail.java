package helloemail.multiplerecipients.infrastructure;

import helloemail.common.infrastructure.requests.EmailRequest;
import helloemail.multiplerecipients.application.MultipleRecipientsApp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail/api")
public class MultipleRecipientsEmail {

    private final MultipleRecipientsApp multiplerecipientsapp;

    public MultipleRecipientsEmail(
            MultipleRecipientsApp multiplerecipientsapp //el objeto siempre en minusculas
    ) {
       this.multiplerecipientsapp = multiplerecipientsapp;
    }

    @PostMapping("/sendToMultipleRecipients")
    public String sendToMultipleRecipients(@RequestBody EmailRequest request) {
        String[] recipients = request.getTo().split("\\s*,\\s*"); // separa por vírgula e ignora espaços
        multiplerecipientsapp.sendEmail(recipients, request.getSubject(), request.getBody());
        return "OK: múltiplos destinatários";
    }
}

// fazer um arquivo desses pra cada um dos métodos.