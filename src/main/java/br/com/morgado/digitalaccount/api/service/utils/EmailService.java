package br.com.morgado.digitalaccount.api.service.utils;

import java.io.UnsupportedEncodingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.morgado.digitalaccount.api.domain.model.UserModel;
import br.com.morgado.digitalaccount.api.exception.BusinessRuleException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private static final String SENDER_EMAIL = "digitalac@email.com";
    private static final String SENDER_NAME = "Digital Account";

    public static final String URL_SITE = "http://localhost:8080/api";

    @Async
    private void enviarEmail(String userEmail, String title, String msg) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(SENDER_EMAIL, SENDER_NAME);
            helper.setTo(userEmail);
            helper.setSubject(title);
            helper.setText(msg, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new BusinessRuleException("Error sending email");
        }

        javaMailSender.send(message);
    }

    public void sendVerificationEmail(UserModel userModel) {
        String title = "Here is your link to verify your email";
        String msg = gerarConteudoEmail("Hello [[name]],<br>"
                + "Please click the link below to verify your account:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Digital Account :).", userModel.getFullName(),
                URL_SITE + "/verify-account?code=" + userModel.getToken());

        enviarEmail(userModel.getUsername(), title, msg);
    }

    private String gerarConteudoEmail(String template, String name, String url) {
        return template.replace("[[name]]", name).replace("[[URL]]", url);
    }

}
