package br.ppii.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ppii.model.Oferta;

public interface OfertaDAO extends JpaRepository<Oferta, Integer>  {


}
