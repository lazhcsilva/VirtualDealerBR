package br.ppii.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "assinatura")
public class Assinatura {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idAssinatura;
	
	private String nomeAssinatura;
	
	private String validade;
	
	@Size(min = 5, max = 255)
	private String operacao;
	
	private double preco;
	
	private String descricao;
	private String descricao2;
	private String descricao3;
	
	@OneToOne
	private Cliente cliente;
	
	@OneToOne
	private PlanoAssinatura planoAssinatura;

	public Integer getIdAssinatura() {
		return idAssinatura;
	}

	public void setIdAssinatura(Integer idAssinatura) {
		this.idAssinatura = idAssinatura;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public PlanoAssinatura getPlanoAssinatura() {
		return planoAssinatura;
	}

	public void setPlanoAssinatura(PlanoAssinatura planoAssinatura) {
		this.planoAssinatura = planoAssinatura;
	}

	public String getNomeAssinatura() {
		return nomeAssinatura;
	}

	public void setNomeAssinatura(String nomeAssinatura) {
		this.nomeAssinatura = nomeAssinatura;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Assinatura [idAssinatura=" + idAssinatura + ", nomeAssinatura=" + nomeAssinatura + ", validade="
				+ validade + ", operacao=" + operacao + ", preco=" + preco + ", descricao=" + descricao + ", cliente="
				+ cliente + ", planoAssinatura=" + planoAssinatura + "]";
	}

	public String getDescricao3() {
		return descricao3;
	}

	public void setDescricao3(String descricao3) {
		this.descricao3 = descricao3;
	}

	public String getDescricao2() {
		return descricao2;
	}

	public void setDescricao2(String descricao2) {
		this.descricao2 = descricao2;
	}

	
}
