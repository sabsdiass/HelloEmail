package helloemail.infrastructure.api;

import helloemail.application.SimpleEmailApp;
import helloemail.infrastructure.common.requests.EmailRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail/api")
public class SimpleEmail {

    private final SimpleEmailApp simpleMailApp;

    public SimpleEmail(
            SimpleEmailApp aSimpleMailApp
    ) {
        simpleMailApp = aSimpleMailApp;
    }

    @PostMapping("/sendSimpleMail")
    public String sendSimpleMail(@RequestBody EmailRequest request) {
        simpleMailApp.sendEmail(request.getTo(), request.getSubject(), request.getBody());
        return "OK: simple mail enviado";
    }
}
