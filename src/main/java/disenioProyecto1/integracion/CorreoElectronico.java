/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package disenioProyecto1.integracion;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Nelson
 */
public class CorreoElectronico {


    private static final String emailFrom = "jesuschavarria483@gmail.com";
    private static final String passwordFrom = "yhob svgb srut wpbz";
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public CorreoElectronico() {
        mProperties = new Properties();
        setupProperties();
    }

    private void setupProperties() {
        // ConfiguraciÃ³n del protocolo SMTP
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        
        mSession = Session.getDefaultInstance(mProperties);
    }

    private void createEmail(String emailTo, String subject, String content, String filePath) {
        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);

            // Cuerpo del mensaje
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(content, "ISO-8859-1", "html");

            // Archivo adjunto
            MimeBodyPart attachmentPart = new MimeBodyPart();
            File file = new File(filePath);
            FileDataSource source = new FileDataSource(file);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(file.getName());

            // Combinamos todo en un multipart
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);  // Texto del mensaje
            multipart.addBodyPart(attachmentPart);   // Archivo adjunto

            mCorreo.setContent(multipart);  // Adjuntamos al correo

        } catch (MessagingException ex) {
            Logger.getLogger(CorreoElectronico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            System.out.println("Correo enviado");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(CorreoElectronico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(CorreoElectronico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void mandar(String emailDestino, String Sujeto, String contenido, String filePath) {
        CorreoElectronico envioCorreos = new CorreoElectronico();
        envioCorreos.createEmail(emailDestino, Sujeto, contenido, filePath);
        envioCorreos.sendEmail();
    }
  
}
