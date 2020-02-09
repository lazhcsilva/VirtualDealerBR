package br.ppii.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ppii.model.Cliente;
import br.ppii.model.Email;
import br.ppii.model.EmailMensagem;
import br.ppii.persistence.EmailDAO;

@Service
public class EmailService {

	private final String username = "cupuladoti@gmail.com";
	private final String password = "projeto2019";
	
	@Autowired
	private EmailDAO emailDAO;
	
	public Email findByToken(String token) {
		
		return this.emailDAO.findByToken(token);
		
	}
	
	public void salvarRegistro(Email email) {
		
		this.emailDAO.save(email);
		
	}
	
	public boolean validarVencimento(Email email) {
		
		LocalDate agora = LocalDate.now();
		
		if(email.getValidade().isAfter(agora)) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public void enviarConfirmacaoDeConta(Cliente cliente) throws MessagingException {
		
		LocalDate agora = LocalDate.now();
		LocalDate validade = agora.plusDays(2);
		
		EmailMensagem mensagem = new EmailMensagem();
		mensagem.setTitulo("Olá, " + cliente.getNome() + ". Ative sua conta!");
		mensagem.setMensagem("Para ter acesso total ao nosso site, ative sua conta, copiando esse endereço abaixo e colando na url. (este email vence em: " +  validade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")");
		
		mensagem.setController("/ativarConta");
		
		Email emailConfirmacao = new Email();
		emailConfirmacao.setAssunto("VirtualDealer - confirme sua conta!");
		emailConfirmacao.setMensagem(mensagem);
		emailConfirmacao.setNomeRemetente("VirtualDealer");
		emailConfirmacao.setEmailRemetente("cupuladoti@gmail.com");
		emailConfirmacao.setNomeDestinatario(cliente.getNome());
		emailConfirmacao.setEmailDestinatario(cliente.getEmailCliente());
		emailConfirmacao.setValidade(validade);
		
		boolean comTolken = true;
		
		this.sendEmailTSL(emailConfirmacao, comTolken);
		
	}
	
	public void sendEmailTSL(Email email, boolean comTolken) throws MessagingException {
		
		email.setToken(UUID.randomUUID().toString());
		email.getMensagem().setTokenEmail(email.getToken());
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(username, password);
				
			}
			
		});
		
		session.setDebug(true);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email.getEmailRemetente()));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getEmailDestinatario()));
		message.setSubject(email.getAssunto());
		message.setContent(email.getMensagem().gerarMensagemComTolken(comTolken), "text/html");

		Transport.send(message);
		this.emailDAO.save(email);
		
	}
	
	public void enviarRecuperacaoDeSenha(String email, String nome, String novasenha) throws MessagingException{
		
		EmailMensagem mensagem = new EmailMensagem();
		mensagem.setTitulo("Olá, " +  nome + "  recupere sua senha!");
		mensagem.setMensagem("Sua nova senha é:  " + novasenha + "  (Não se preocupe, você pode muda-la em nossa pagina ')");
				;

		Email recuperacaoDeSenha =  new Email();
		recuperacaoDeSenha.setAssunto("VirtualDealer - Recuperação de senha!");
		recuperacaoDeSenha.setMensagem(mensagem);
		recuperacaoDeSenha.setNomeRemetente("VirtualDealer");
		recuperacaoDeSenha.setEmailRemetente("cupuladoti@gmail.com");
		recuperacaoDeSenha.setNomeDestinatario(nome);
		recuperacaoDeSenha.setEmailDestinatario(email);
 
		boolean semTolken = false;
		
		this.sendEmailTSL(recuperacaoDeSenha, semTolken);
				
	}
	
}