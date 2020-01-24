package br.ppii.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idReserva;
	
	@Temporal(TemporalType.DATE)
	private Date dataReserva;
	
	@Temporal(TemporalType.TIME)
	private Date horario;
	
	@ManyToOne
	private Cliente cliente;

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", dataReserva=" + dataReserva + ", horario=" + horario
				+ ", cliente=" + cliente + "]";
	}
	
	
	
}