package br.ppii.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ppii.model.Foto;

public interface FotoDAO extends JpaRepository<Foto, Integer> {

	Optional<Foto> findById(String id);
	
}
