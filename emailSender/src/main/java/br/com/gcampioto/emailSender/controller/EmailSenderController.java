package br.com.gcampioto.emailSender.controller;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by gcampioto on 12/04/17.
 */
@RequestMapping("/email")
@Controller
public class EmailSenderController {
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@RequestParam("name") String name, @RequestParam("email") String emailAddress){
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("SEU_EMAIL@seuEmail.com", "SUA_SENHA"));
            email.setSSLOnConnect(true);

            email.setFrom("SEU_EMAIL@seuemail.com");
            email.setSubject("Você foi convidado pelo ListaVIP");
            email.setMsg("Olá " + name + ". Você acaba de ser convidado pelo ListaVIP.");
            email.addTo(emailAddress);
            //email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
