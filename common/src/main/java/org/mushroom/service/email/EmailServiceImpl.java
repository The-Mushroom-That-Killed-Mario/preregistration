package org.mushroom.service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public void sendRegistrationEmail(String to, String login) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject("Registration in prereg-service");
        helper.setText(getHtmlContent(login), true);
        mailSender.send(message);
    }

    private String getHtmlContent(String login) {
        Context context = new Context();
        context.setVariable("login", login);
        return templateEngine.process("UserRegistrationMail", context);
    }
}
