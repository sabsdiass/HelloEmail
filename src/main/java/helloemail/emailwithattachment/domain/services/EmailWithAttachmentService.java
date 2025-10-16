package helloemail.emailwithattachment.domain.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailWithAttachmentService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    public EmailWithAttachmentService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWithAttachment(String to, String subject, String htmlContent)
            throws MessagingException, IOException {

        // 1) Cria a mensagem MIME
        MimeMessage mime = mailSender.createMimeMessage();
        // multipart=true para permitir anexos; charset UTF-8
        MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");

        // 2) Cabeçalhos e corpo
        helper.setFrom(fromAddress);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        // 3) Carrega anexo a partir do classpath
        ClassPathResource file = new ClassPathResource("attachment.jpg");
        if (!file.exists()) {
            throw new IOException("Anexo não encontrado em src/main/resources/attachment.jpg");
        }
        helper.addAttachment("attachment.jpg", file);

        // 4) Envia
        mailSender.send(mime);
    }
}