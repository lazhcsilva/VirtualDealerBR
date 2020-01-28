package br.ppii.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.ppii.enuns.TipoEmail;

@Service
public class EmailService {

	@Autowired
	JavaMailSender mailSender;
	
	public void enviarEmail(String destinatario, String assunto, String corpo, TipoEmail tipo) {
		
		new Thread() {
			
			@Override
			public void run() {
				
				if(tipo.equals(TipoEmail.TEXTO)) {
					
					SimpleMailMessage message = new SimpleMailMessage();
					message.setSubject(assunto);
					message.setText(corpo);
					message.setTo(destinatario);
					message.setFrom("VirtualDealer <cupuladoti@gmail.com>");
					
					try {
						
						mailSender.send(message);
						
					} catch (Exception e) {
						
						throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage());
						
					}
					
				} else {
					
					MimeMessage mail = mailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(mail);
					
					try {
						
						helper.setSubject(assunto);
						helper.setText(corpo, true);
						helper.setTo(destinatario);
						helper.setFrom("VirtualDealer <cupuladoti@gmail.com>");
						
					} catch (Exception e) {
						
						System.out.println(e.getMessage());
						throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage());
						
					}
					
				}
				
			}
			
		}.start();
		
	}
	
}
