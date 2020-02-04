package br.ppii.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name="concessionaria")
public class Concessionaria {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idConcessionaria;
	
	@Size(min = 5, max = 40)
	@NotBlank(message="O nome deve ser preenchido corretamente com texto válido")
	private String concessionaria;
	
	@Size(min = 5, max = 40)
	@NotBlank(message="O nome deve ser preenchido corretamente com texto válido")
	private String razaoSocial;
	
	@Email
	@Column(name = "email_concessionaria")
	@NotBlank(message="O email deve ser preenchido corretamente")
	private String emailConcessionaria;
	
	@Size(min = 5, max = 40)
	@Column(name = "nome_fantasia")
	@NotBlank(message="O nome fantasia deve ser preenchido corretamente com texto válido")
	private String nomeFantasia;
	
	@Column(name = "inscricao_estadual")
	private String inscricaoEstadual;
	
	@NotBlank(message="Digite uma senha válida contendo uma letra maiuscula uma miniscula e um número")
	private String password;
	
	@CNPJ
	@NotBlank(message="Digite CNPJ válido")
	private String cnpj;
	
	@NotBlank(message="Deve ser preenchido")
	private String telefone1;
	
	private String fotoConcessionaria;
	
	@OneToOne
	private Endereco endereco;
	
	@ManyToMany
	private List<Oferta> oferta;
	
	public Concessionaria() {};

	public Concessionaria(Integer idConcessionaria,
			@Size(min = 5, max = 40) @NotBlank(message = "O nome deve ser preenchido corretamente com texto válido") String concessionaria,
			@Size(min = 5, max = 40) @NotBlank(message = "O nome deve ser preenchido corretamente com texto válido") String razaoSocial,
			@Email @NotBlank(message = "O email deve ser preenchido corretamente") String emailConcessionaria,
			@Size(min = 5, max = 40) @NotBlank(message = "O nome fantasia deve ser preenchido corretamente com texto válido") String nomeFantasia,
			String inscricaoEstadual,
			@NotBlank(message = "Digite uma senha válida contendo uma letra maiuscula uma miniscula e um número") String password,
			@CNPJ @NotBlank(message = "Digite CNPJ válido") String cnpj,
			@NotBlank(message = "Deve ser preenchido") String telefone1, String fotoConcessionaria, Endereco endereco,
			List<Oferta> oferta) {
		super();
		this.idConcessionaria = idConcessionaria;
		this.concessionaria = concessionaria;
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

	public String getConcessionaria() {
		return concessionaria;
	}

	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
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

	public String getFotoConcessionaria() {
		return fotoConcessionaria;
	}

	public void setFotoConcessionaria(String fotoConcessionaria) {
		this.fotoConcessionaria = fotoConcessionaria;
	}

	@Override
	public String toString() {
		return "Concessionaria [idConcessionaria=" + idConcessionaria + ", concessionaria=" + concessionaria
				+ ", razaoSocial=" + razaoSocial + ", emailConcessionaria=" + emailConcessionaria + ", nomeFantasia="
				+ nomeFantasia + ", inscricaoEstadual=" + inscricaoEstadual + ", password=" + password + ", cnpj="
				+ cnpj + ", telefone1=" + telefone1 + ", fotoConcessionaria=" + fotoConcessionaria + ", endereco="
				+ endereco + ", oferta=" + oferta + "]";
	}
	
	
	
}