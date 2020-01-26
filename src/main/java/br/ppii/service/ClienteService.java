package br.ppii.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ppii.model.Cliente;
import br.ppii.persistence.ClienteDAO;
import br.ppii.util.Util;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;
	
	public Cliente findClienteByEmail(String emailCliente) {
		return clienteDAO.findByEmailIgnoreCase(emailCliente);
	}
	
	public Cliente findClienteByCpf(String cpf) {
		return clienteDAO.findByCpfIgnoreCase(cpf);
	}
	
	public boolean criarCliente(Cliente cliente)throws ServiceException, MessagingException {
		
		if (this.findClienteByEmail(cliente.getEmailCliente()) != null) {
			throw new ServiceException("Já existe um usuário com este e-mail");
		} 
		else if (this.findClienteByCpf(cliente.getCpf()) != null) {
			throw new ServiceException("Já existe um usuário com este cpf");
		}  else {
				String senhaCriptografada;
				try {
					senhaCriptografada = Util.criptografarSenha(cliente.getPassword());
					cliente.setPassword(senhaCriptografada);
					this.clienteDAO.save(cliente);	
				} catch (Exception e) {
					// TODO: handle exception
				}
				this.save(cliente);
				return true;
	       
		}	
		 
	}
	
	public void save(Cliente cliente) {
	
		this.clienteDAO.save(cliente);
	
	}
	
	public Cliente clienteLogin(String emailCliente, String password) throws ServiceException, NoSuchAlgorithmException, UnsupportedEncodingException {	
		
		String senhaCriptografada = Util.criptografarSenha(password);
		Cliente cliente = this.clienteDAO.clienteLogin(emailCliente, senhaCriptografada);

		if (cliente == null) {
			throw new ServiceException("Login/senha não encontrados");
		}

		return cliente;
	}
	
}
