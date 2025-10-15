package helloemail.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddress; // o Spring injeta – ignore o warning do IntelliJ

    // 👇 ajuste para um arquivo REAL no seu Mac
    private static final String IMAGE = "/Users/SEU_USUARIO/Desktop/teste.pdf";

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // --- texto simples (1 destinatário)
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAddress);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        mailSender.send(msg);
    }

    // --- texto simples (N destinatários)
    public void sendToMultipleRecipients(String[] to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAddress);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        mailSender.send(msg);
    }

    // --- HTML (N destinatários)
    public void sendHTMLMessage(String[] to, String subject, String htmlBody) {
        try {
            MimeMessage mime = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mime, "UTF-8");
            helper.setFrom(fromAddress);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            mailSender.send(mime);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail HTML", e);
        }
    }

    // --- HTML + ANEXO (1 destinatário) — assinatura igual à do controller do professor
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
