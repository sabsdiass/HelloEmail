package helloemail.controller;

import helloemail.common.infrastructure.requests.EmailRequest;
import helloemail.simpleemail.domain.services.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail/api")
public class HelloEmailController {

    private final EmailService emailService;

    public HelloEmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    // TODO: Aplica la misma estructura que con el SimpleSendEmail 😉
    // vou quitando os métodos e agregando nas pastas, aqui já não está o método do sendsimpleemail,
    //  ele transformou em 3 classes
    // crear solo para el sendemailmultiples, y apartir de entonces seguimos.
    // probar (reto), coger la estructura y transformarla de tal manera que el primer nivel sea una
    //carpeta llamada simpleMain, crear 3 carpetas, app, infra y domain. ellas ya estan creadas


}
