package br.ppii.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gestor {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGestor;
	
	private String gestor;
	
	private String emailGestor;
	
	private String password;
	
	private LocalDate dataNascimento;
	
	private String CPF;
	
	private String telefone;
	
	private String fotoGestor;
	
	private int permissao = 2;

	public Gestor() {}

	public Gestor(Integer idGestor, String gestor, String emailGestor, String password, LocalDate dataNascimento,
			String cPF, String telefone, String fotoGestor, int permissao) {
		super();
		this.idGestor = idGestor;
		this.gestor = gestor;
		this.emailGestor = emailGestor;
		this.password = password;
		this.dataNascimento = dataNascimento;
		CPF = cPF;
		this.telefone = telefone;
		this.fotoGestor = fotoGestor;
		this.permissao = permissao;
	}

	public Integer getIdGestor() {
		return idGestor;
	}

	public void setIdGestor(Integer idGestor) {
		this.idGestor = idGestor;
	}

	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

	public String getEmailGestor() {
		return emailGestor;
	}

	public void setEmailGestor(String emailGestor) {
		this.emailGestor = emailGestor;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFotoGestor() {
		return fotoGestor;
	}

	public void setFotoGestor(String fotoGestor) {
		this.fotoGestor = fotoGestor;
	}

	public int getPermissao() {
		return permissao;
	}

	public void setPermissao(int permissao) {
		this.permissao = permissao;
	}

	@Override
	public String toString() {
		return "Gestor [idGestor=" + idGestor + ", gestor=" + gestor + ", emailGestor=" + emailGestor + ", password="
				+ password + ", dataNascimento=" + dataNascimento + ", CPF=" + CPF + ", telefone=" + telefone
				+ ", fotoGestor=" + fotoGestor + ", permissao=" + permissao + "]";
	};
	

}