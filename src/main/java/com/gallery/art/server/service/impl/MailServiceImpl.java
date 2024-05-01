package com.gallery.art.server.service.impl;

import com.gallery.art.server.dto.responce.ResponseMessage;
import com.gallery.art.server.service.IMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements IMailService {

    @Value("${spring.mail.link}")
    private String link;

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender mailSender;
    @Override
    public ResponseEntity<?> sendConfirmEmail(String emailTo, String code) {
        MimeMessage subMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(subMessage);

        try {
            helper.setFrom(sender);
            helper.setTo(emailTo);
            helper.setSubject("GalleryArt Код подтверждения");
            String message = "<html><body>Для подтверждения почты перейдите по : <br> <a href='" + link + "/api/gallery/auth/confirm?email=" + emailTo + "&code=" + code + "'>ссылке</a></body></html>";
            helper.setText(message, true);
            mailSender.send(subMessage);
        }
        catch (MessagingException ex) {
            log.error(MessageFormat.format("Ошибка при попытке отправить сообщение на почту: {0}", ex.getMessage()));
            return ResponseEntity.badRequest().body(new ResponseMessage(MessageFormat.format("Ошибка при попытке отправить сообщение на почту: {0}", ex.getMessage())));
        }
        return ResponseEntity.ok(new ResponseMessage("Письмо с уведомлением о подписке отправлено на почту"));
    }
}
