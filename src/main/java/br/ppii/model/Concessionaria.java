package br.ppii.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="concessionaria")
public class Concessionaria {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idConcessionaria;
	
	private String nomeConcessionaria;
	
	private String razaoSocial;
	
	private String emailConcessionaria;
	
	private String nomeFantasia;
	
	private String inscricaoEstadual;
	
	private String password;
	
	private String cnpj;
	
	private String telefone1;
	
	private String fotoConcessionaria;
	
	@OneToOne
	private Endereco endereco;
	
	@ManyToMany
	private List<Oferta> oferta;

	public Concessionaria() {}
	
	public Concessionaria(Integer idConcessionaria, String nomeConcessionaria, String razaoSocial,
			String emailConcessionaria, String nomeFantasia, String inscricaoEstadual, String password, String cnpj,
			String telefone1, String fotoConcessionaria, Endereco endereco, List<Oferta> oferta) {
		super();
		this.idConcessionaria = idConcessionaria;
		this.nomeConcessionaria = nomeConcessionaria;
		this.razaoSocial = razaoSocial;
		this.emailConcessionaria = emailConcessionaria;
		this.nomeFantasia = nomeFantasia;
		this.inscricaoEstadual = inscricaoEstadual;
		this.password = password;
		this.cnpj = cnpj;
		this.telefone1 = telefone1;
		this.fotoConcessionaria = fotoConcessionaria;
		this.endereco = endereco;
		this.oferta = oferta;
	}

	public Integer getIdConcessionaria() {
		return idConcessionaria;
	}

	public void setIdConcessionaria(Integer idConcessionaria) {
		this.idConcessionaria = idConcessionaria;
	}

	public String getNomeConcessionaria() {
		return nomeConcessionaria;
	}

	public void setNomeConcessionaria(String nomeConcessionaria) {
		this.nomeConcessionaria = nomeConcessionaria;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getEmailConcessionaria() {
		return emailConcessionaria;
	}

	public void setEmailConcessionaria(String emailConcessionaria) {
		this.emailConcessionaria = emailConcessionaria;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getFotoConcessionaria() {
		return fotoConcessionaria;
	}

	public void setFotoConcessionaria(String fotoConcessionaria) {
		this.fotoConcessionaria = fotoConcessionaria;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Oferta> getOferta() {
		return oferta;
	}

	public void setOferta(List<Oferta> oferta) {
		this.oferta = oferta;
	}

	@Override
	public String toString() {
		return "Concessionaria [idConcessionaria=" + idConcessionaria + ", nomeConcessionaria=" + nomeConcessionaria
				+ ", razaoSocial=" + razaoSocial + ", emailConcessionaria=" + emailConcessionaria + ", nomeFantasia="
				+ nomeFantasia + ", inscricaoEstadual=" + inscricaoEstadual + ", password=" + password + ", cnpj="
				+ cnpj + ", telefone1=" + telefone1 + ", fotoConcessionaria=" + fotoConcessionaria + ", endereco="
				+ endereco + ", oferta=" + oferta + "]";
	}
	
}