package br.ppii.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "planoAssinatura")
public class PlanoAssinatura {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPlanoAssinatura;
	
	@NotNull
	private String descricao;
	
	private double preco;
	
	@OneToOne
	private Assinatura assinatura;

	public Integer getIdPlanoAssinatura() {
		return idPlanoAssinatura;
	}

	public void setIdPlanoAssinatura(Integer idPlanoAssinatura) {
		this.idPlanoAssinatura = idPlanoAssinatura;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Assinatura getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(Assinatura assinatura) {
		this.assinatura = assinatura;
	}

	@Override
	public String toString() {
		return "PlanoAssinatura [idPlanoAssinatura=" + idPlanoAssinatura + ", descricao=" + descricao + ", preco="
				+ preco + ", assinatura=" + assinatura + "]";
	}
	
}