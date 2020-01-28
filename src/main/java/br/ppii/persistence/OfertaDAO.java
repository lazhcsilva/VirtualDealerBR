package br.ppii.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.ppii.model.Oferta;

@RepositoryRestResource(path= "oferta", collectionResourceRel = "oferta")
public interface OfertaDAO extends JpaRepository<Oferta, Integer>  {

	
	

}
