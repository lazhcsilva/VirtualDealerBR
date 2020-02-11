package br.ppii.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ppii.model.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

	@Query("select c from Cliente c where c.emailCliente = :emailCliente and c.password = :password and c.ativo=true"  )
	public Cliente efetuarLogin(String emailCliente, String password);
	
	@Query("select c from Cliente c where c.emailCliente = :emailCliente")
	public Cliente findByEmailIgnoreCase(String emailCliente);
	
	@Query("select c from Cliente c where c.cpf = :cpf")
	public Cliente findByCpfIgnoreCase(String cpf);
	
	@Query("select c from Cliente c where c.idCliente = :idCliente")
	public Cliente findByid(@Param("idCliente") Integer idCliente);
}
