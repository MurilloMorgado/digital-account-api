package br.com.morgado.digitalaccount.api.service.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.morgado.digitalaccount.api.domain.model.UserModel;
import br.com.morgado.digitalaccount.api.exception.BusinessRuleException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    
    @Value("${spring.mail.username}")
    private String senderEmail;

    private static final String SENDER_NAME = "Digital Account";

    public static final String URL_SITE = "http://localhost:8080/api/users";

    @Async
    public void sendEmail(String userEmail, String title, String msg) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(senderEmail, SENDER_NAME);
            helper.setTo(userEmail);
            helper.setSubject(title);
            helper.setText(msg, true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessRuleException("Error sending email: " + e.getMessage());
        }

        javaMailSender.send(message);
    }

    public void sendVerificationEmail(UserModel userModel) {
        String title = "Here is your link to verify your email";
        String msg = generateEmailContent("Hello [[name]],<br>"
                + "Please click the link below to verify your account:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Digital Account :).", userModel.getFullName(),
                URL_SITE + "/verify-account?code=" + userModel.getToken());

        sendEmail(userModel.getUsername(), title, msg);
    }

    private String generateEmailContent(String template, String name, String url) {
        return template.replace("[[name]]", name).replace("[[URL]]", url);
    }

}
