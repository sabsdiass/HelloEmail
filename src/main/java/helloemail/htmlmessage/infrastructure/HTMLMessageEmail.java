package helloemail.htmlmessage.infrastructure;

import helloemail.common.infrastructure.requests.EmailRequest;
import helloemail.htmlmessage.application.HTMLMessageApp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail/api")
public class HTMLMessageEmail {

    private final HTMLMessageApp htmlmessageapp;

    public HTMLMessageEmail(
            HTMLMessageApp htmlmessageapp //el objeto siempre en minusculas
    ) {
        this.htmlmessageapp = htmlmessageapp;
    }

    @PostMapping("/sendHTMLMessage")
    public String sendHTMLMessage(@RequestBody EmailRequest request) {
        String[] recipients = request.getTo().split("\\s*,\\s*"); // separa por vírgula e ignora espaços
        htmlmessageapp.sendHTMLMessage(recipients, request.getSubject(), request.getBody());
        return "OK: HTML mail enviado";
    }
}