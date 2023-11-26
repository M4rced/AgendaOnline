/*package co.com.agendaonline.tareas.models.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nimbusds.oauth2.sdk.Message;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.websocket.Session;

import java.net.PasswordAuthentication;
import java.util.Properties;

@Service
public class GmailNotificationService {
	
	@Value("${gmail.api.credentials.path}")
    private String credentialsPath; // Ruta al archivo JSON de credenciales

    public void enviarNotificacion(String destinatario, String asunto, String cuerpo) {
        // Configuración de propiedades de JavaMail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Obtiene las credenciales
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tu@gmail.com", "tu-contraseña");
            }
        });

        try {
            // Crear un mensajwe
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tu@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            // Enviar el mensaje
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}*/


