package br.ppii.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ppii.model.Assinatura;

public interface AssinaturaDAO extends JpaRepository<Assinatura, Integer> {

	@Query("select c from Assinatura c where c.nomeAssinatura = :nomeAssinatura")
	public Assinatura findByNome(String nomeAssinatura);
	
}
