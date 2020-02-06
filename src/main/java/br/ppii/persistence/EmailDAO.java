package br.ppii.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ppii.model.Email;

public interface EmailDAO extends JpaRepository<Email, Integer> {

	public Email findByToken(String token);
	
}
