package br.fiocruz.procc.acbmservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private Environment env;

    private String user;

    private String pass;

    public void sendEmailSSL(String to, String subject, String text) {

        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        user = String.valueOf(env.getProperty("userMail"));
        pass = String.valueOf(env.getProperty("passMail"));
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(user, pass);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("acbm.service@gmail.com"));
            //Remetente

            Address[] toUser = InternetAddress.parse(to);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(subject);//Assunto
            message.setText(text);

            Transport.send(message);

            System.out.println("FEITO!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Email enviado com sucesso!");
    }

    public void sendEmail(String to, String subject, String text) {

        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        user = "acbm.service@gmail.com";
        pass = "ypczrmajwtxozsan";

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(user, pass);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("acbm.service@gmail.com"));
            //Remetente

            Address[] toUser = InternetAddress.parse(to);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(subject);//Assunto
            message.setText(text);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Email enviado com sucesso!");
    }

    public void sendEmailWithAttachment(String to, String subject, String text, String pathFile) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        user = String.valueOf(env.getProperty("userMail"));
        pass = String.valueOf(env.getProperty("passMail"));

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("acbm.service@gmail.com"));

            Address[] toUser = InternetAddress.parse(to);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachmenBodytPart = new MimeBodyPart();
            MimeBodyPart textPart = new MimeBodyPart ();

            attachmenBodytPart.attachFile(new File(pathFile));
            multipart.addBodyPart(attachmenBodytPart);

            textPart.setText(text);
            multipart.addBodyPart(textPart);

            message.setContent(multipart);
            Transport.send(message);

            System.out.println("FEITO!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Email enviado com sucesso!");
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(text);
//
//        FileSystemResource file = new FileSystemResource(new File(pathFile));
//        helper.addAttachment(file.getFilename(), file);
//
//        javaMailSender.send(message);
//
//        System.out.println("Email enviado com sucesso!");
    }
}
