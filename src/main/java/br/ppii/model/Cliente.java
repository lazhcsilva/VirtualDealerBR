package br.ppii.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "cliente")
public class Cliente{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCliente;
	
	private String nome;
	
	private String emailCliente;
	
	private String password;
	
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	private String telefone;
	
	private String fotoCliente;
	
	private boolean ativo;
	
	@ManyToOne
	private Assinatura assinatura;
	
	@ManyToOne(optional=true,cascade=CascadeType.ALL)
	private Endereco endereco;
	
	@ManyToOne
	private Reserva reserva;
	
	public Cliente() {}

	public Cliente(Integer idCliente, String nome, String emailCliente, String password, String cpf,
			Date dataNascimento, String telefone, String fotoCliente, boolean ativo, Assinatura assinatura,
			Endereco endereco, Reserva reserva) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.emailCliente = emailCliente;
		this.password = password;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.fotoCliente = fotoCliente;
		this.ativo = ativo;
		this.assinatura = assinatura;
		this.endereco = endereco;
		this.reserva = reserva;
	}



	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Assinatura getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(Assinatura assinatura) {
		this.assinatura = assinatura;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	public String getFotoCliente() {
		return fotoCliente;
	}

	public void setFotoCliente(String fotoCliente) {
		this.fotoCliente = fotoCliente;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", emailCliente=" + emailCliente + ", password="
				+ password + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", telefone=" + telefone
				+ ", fotoCliente=" + fotoCliente + ", ativo=" + ativo + ", assinatura=" + assinatura + ", endereco="
				+ endereco + ", reserva=" + reserva + "]";
	}

	
}