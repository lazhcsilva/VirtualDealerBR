package br.ppii.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "oferta")
public class Oferta {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOferta;
	
	private String veiculo;
	
	private String tipoVeiculo;
	
	private String marca;
	
	private String cor;
	
	private String placa;
	
	private String chassi;
	
	private boolean importado;
	
	private String estado;
	
	private String combustivel;
	
	private double valor;
	
	private String descricao;
	
	private String renavam;
	
	private String ano;
	
	private String fotoOferta;
	
	@ManyToOne
	private Concessionaria concessionaria;
	
	
	public Oferta() {};

	public Oferta(Integer idOferta, String veiculo, String tipoVeiculo, String marca, String cor, String placa,
			String chassi, boolean importado, String estado, String combustivel, double valor, String descricao,
			String renavam, String ano, String fotoOferta, Concessionaria concessionaria) {
		super();
		this.idOferta = idOferta;
		this.veiculo = veiculo;
		this.tipoVeiculo = tipoVeiculo;
		this.marca = marca;
		this.cor = cor;
		this.placa = placa;
		this.chassi = chassi;
		this.importado = importado;
		this.estado = estado;
		this.combustivel = combustivel;
		this.valor = valor;
		this.descricao = descricao;
		this.renavam = renavam;
		this.ano = ano;
		this.fotoOferta = fotoOferta;
		this.concessionaria = concessionaria;
	}

	public Integer getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Integer idOferta) {
		this.idOferta = idOferta;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public boolean isImportado() {
		return importado;
	}

	public void setImportado(boolean importado) {
		this.importado = importado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Concessionaria getConcessionaria() {
		return concessionaria;
	}

	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getFotoOferta() {
		return fotoOferta;
	}

	public void setFotoOferta(String fotoOferta) {
		this.fotoOferta = fotoOferta;
	}

	@Override
	public String toString() {
		return "Oferta [idOferta=" + idOferta + ", veiculo=" + veiculo + ", tipoVeiculo=" + tipoVeiculo + ", marca="
				+ marca + ", cor=" + cor + ", placa=" + placa + ", chassi=" + chassi + ", importado=" + importado
				+ ", estado=" + estado + ", combustivel=" + combustivel + ", valor=" + valor + ", descricao="
				+ descricao + ", renavam=" + renavam + ", ano=" + ano + ", fotoOferta=" + fotoOferta
				+ ", concessionaria=" + concessionaria + "]";
	}

}