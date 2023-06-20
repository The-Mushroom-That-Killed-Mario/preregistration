package org.mushroom.service.email;

import javax.mail.MessagingException;

public interface EmailService {
    void sendRegistrationEmail(String to, String login) throws MessagingException;
}
