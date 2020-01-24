package br.ppii.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ppii.model.Concessionaria;

public interface ConcessionariaDAO extends JpaRepository<Concessionaria, Integer> {

	@Query("select c from Concessionaria c where c.emailConcessionaria = :emailConcessionaria and c.password = :password")
	public Concessionaria concessionariaLogin(String emailConcessionaria, String password);
	
	@Query("select c from Concessionaria c where c.emailConcessionaria = :emailConcessionaria")
	public Concessionaria findByEmailIgnoreCase(String emailConcessionaria);
	
	@Query("select c from Concessionaria c where c.cnpj = :cnpj")
	public Concessionaria findByCnpjIgnoreCase(String cnpj);

	@Query("select c from Concessionaria c where c.inscricaoEstadual = :inscricaoEstadual")
	public Concessionaria findByInscricaoEstadualIgnoreCase(String inscricaoEstadual);
	
	@Query("select c from Concessionaria c where c.razaoSocial = :razaoSocial")
	public Concessionaria findByRazaoSocialIgnoreCase(String razaoSocial);
	
}
