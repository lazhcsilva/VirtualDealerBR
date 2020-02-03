package br.ppii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ppii.model.Assinatura;
import br.ppii.persistence.AssinaturaDAO;

@Service
public class AssinaturaService {

	@Autowired
	private AssinaturaDAO assinaturaDAO;
	
	public List<Assinatura>listarTodos(Sort ordenacao) {
		return this.assinaturaDAO.findAll(ordenacao);
	}
	
	public Assinatura obterPorId(Integer idAssinatura) {
		return this.assinaturaDAO.getOne(idAssinatura);
	}
	
	public void remover(Integer idAssinatura) {
		
		this.assinaturaDAO.deleteById(idAssinatura);
		
	}
	
	public Assinatura novaAssinatura(Assinatura assinatura){
		
		Assinatura assinaturaExistente = this.assinaturaDAO.findByNome(assinatura.getNomeAssinatura());
		
		if (assinaturaExistente == null) {
			this.save(assinatura);
			return (assinatura);
		}
		
		return assinatura;
	}
	
	public void save(Assinatura assinatura) {
	
		this.assinaturaDAO.save(assinatura);
	
	}
	
	
}
