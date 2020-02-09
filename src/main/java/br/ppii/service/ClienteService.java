package br.ppii.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

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
	
	@Autowired
	private EmailService emailService;
	
	public Cliente findClienteByEmail(String emailCliente) {
		return clienteDAO.findByEmailIgnoreCase(emailCliente);
	}
	
	public Cliente findClienteByCpf(String cpf) {
		return clienteDAO.findByCpfIgnoreCase(cpf);
	}
	
	public Cliente findByEmail(String email) {
		return clienteDAO.findByEmailIgnoreCase(email);
	}
	
	public void criarCliente(Cliente cliente)throws ServiceException, MessagingException {
		
		if (this.findClienteByEmail(cliente.getEmailCliente()) != null) {
			throw new ServiceException("Já existe um usuário com este e-mail");
		} 
		else if (this.findClienteByCpf(cliente.getCpf()) != null) {
			throw new ServiceException("Já existe um usuário com este cpf");
		}  else {
			String senhaCriptografada;
			
			cliente.setToken(UUID.randomUUID().toString());
			
			try {
				senhaCriptografada = Util.criptografarSenha(cliente.getPassword());
				cliente.setPassword(senhaCriptografada);
				this.clienteDAO.save(cliente);	
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			this.emailService.enviarConfirmacaoDeConta(cliente);
			
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
	
	public void reEnviarEmailConfirmacao(String email) throws MessagingException {
		
		Cliente cliente = this.findByEmail(email);

		if (cliente.getAtivo() == false) {
		
			this.emailService.enviarConfirmacaoDeConta(cliente);
		
		}
	}
	public Cliente editarPerfil(Cliente cliente,HttpSession session) {
		Cliente clienteLogado=(Cliente)session.getAttribute("clientelogin");
		Cliente user = this.clienteDAO.findByid(clienteLogado.getIdCliente());
			
		user.setNome(cliente.getNome());
		user.setEmailCliente(cliente.getEmailCliente());
		user.setPassword(cliente.getPassword());
		user.setCpf(cliente.getCpf());
		user.setDataNascimento(cliente.getDataNascimento());
		user.setTelefone(cliente.getTelefone());
		user.setFotoCliente(cliente.getFotoCliente());
		
	
		return user;
	}

	
			
	/*
	 * public boolean salvarUsuario(Cliente cliente) throws ServiceException,
	 * MessagingException;
	 */
	
}