package br.ppii.model;

import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Email {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idEmail;
	
	private String nomeRemetente;
	
	private String emailRemetente;
	
	private String nomeDestinatario;
	
	private String emailDestinatario;
	
	private String assunto;
	
	@Embedded
	private EmailMensagem mensagem;
	
	private String token;
	
	private LocalDate validade;
	
	public Email() {}

	public Integer getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(Integer idEmail) {
		this.idEmail = idEmail;
	}

	public String getNomeRemetente() {
		return nomeRemetente;
	}

	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}

	public String getEmailRemetente() {
		return emailRemetente;
	}

	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public EmailMensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(EmailMensagem mensagem) {
		this.mensagem = mensagem;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public Email(Integer idEmail, String nomeRemetente, String emailRemetente, String nomeDestinatario,
			String emailDestinatario, String assunto, EmailMensagem mensagem, String token, LocalDate validade) {
		super();
		this.idEmail = idEmail;
		this.nomeRemetente = nomeRemetente;
		this.emailRemetente = emailRemetente;
		this.nomeDestinatario = nomeDestinatario;
		this.emailDestinatario = emailDestinatario;
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.token = token;
		this.validade = validade;
	}
	
}