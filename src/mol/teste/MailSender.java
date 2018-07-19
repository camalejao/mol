package mol.teste;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	private static Session criarSessionEmail() {
		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("monitoriaonline.ifal@gmail.com", "");
			}
		});

		session.setDebug(true);

		return session;
	}

	public static void enviarEmail(String destino, String assunto, String mensagem) {
		try {
			String remetente = "monitoriaonline.ifal@gmail.com";
			Message message = new MimeMessage(criarSessionEmail());
			Address[] toAddress = InternetAddress.parse(destino.trim().toLowerCase());
			
			message.setFrom(new InternetAddress(remetente));
			message.setRecipients(Message.RecipientType.TO, toAddress);
	        message.setSubject(assunto);
	        message.setContent(mensagem, "text/html");
	        
	        Transport.send(message);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		enviarEmail("joao.falcao@icloud.com", "teste", "email funcionando");
	}
}
