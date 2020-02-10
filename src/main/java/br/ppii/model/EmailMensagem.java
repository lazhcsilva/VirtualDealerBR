package br.ppii.model;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class EmailMensagem {

	private String titulo;

	@Transient
	private String tokenEmail;
	
	private String mensagem;
	
	private String controller;
	
	public EmailMensagem () {}

	public EmailMensagem(String titulo, String tokenEmail, String mensagem, String controller) {
		super();
		this.titulo = titulo;
		this.tokenEmail = tokenEmail;
		this.mensagem = mensagem;
		this.controller = controller;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTokenEmail() {
		return tokenEmail;
	}

	public void setTokenEmail(String tokenEmail) {
		this.tokenEmail = tokenEmail;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}
	
	public String gerarMensagemComTolken(boolean comTolken) {
		
		StringBuilder mensagem = new StringBuilder();
		
		mensagem.append("<h1>" + this.getTitulo() + "</h1><br><br>");
		mensagem.append("<p>" + this.getMensagem() + "</p><br>");
		
		if ((comTolken == true)) {
			
			mensagem.append("<html><body><a href='http:/localhost:8080" + this.getController() + "?token=" + this.getTokenEmail() + "/" + "<button>Confirmar!<button></a></body></html>");
			
		}
		
		return mensagem.toString();
		
	}
	
}