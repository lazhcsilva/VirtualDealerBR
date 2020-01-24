package br.ppii.service;

import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ppii.model.Oferta;
import br.ppii.persistence.OfertaDAO;

@Service
public class OfertaService {

	@Autowired
	private OfertaDAO ofertaDAO;
	
	public List<Oferta>listarTodos(Sort ordenacao) {
		return this.ofertaDAO.findAll(ordenacao);
	}
	
	public Oferta obterPorId(Integer idOferta) {
		return this.ofertaDAO.getOne(idOferta);
	}
	
	public boolean criarOferta(Oferta oferta) throws ServiceException, MessagingException {
		this.save(oferta);
		return true;
	}
	
	public void save(Oferta oferta) {
		this.ofertaDAO.save(oferta);
	}

	public void remover(Integer idOferta) {
		this.ofertaDAO.deleteById(idOferta);
	}
	
	//listar por Id
	public Oferta findById(Integer id) {
		return ofertaDAO.findById(id).orElse(null);
	}
}