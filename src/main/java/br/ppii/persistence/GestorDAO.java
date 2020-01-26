package br.ppii.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ppii.model.Gestor;

public interface GestorDAO extends JpaRepository<Gestor, Integer> {

	
	@Query("select p from Gestor p where p.emailGestor = :emailGestor and p.password = :password")
	public Gestor gestorLogin(String emailGestor, String password);
	
}