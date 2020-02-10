package br.ppii.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ppii.model.Gestor;
import br.ppii.persistence.GestorDAO;

@Service
public class GestorService {

	@Autowired
	private GestorDAO gestorDAO;
	
	public Gestor logarGestor(String emailGestor, String password) throws ServiceException, NoSuchAlgorithmException, UnsupportedEncodingException {	
		
		Gestor gestor= this.gestorDAO.gestorLogin(emailGestor, password);

		if (gestor == null) {
			throw new ServiceException("Login/senha não encontrados");
		}

		return gestor;
	}
	
	public void save(Gestor gestor) {
		
		this.gestorDAO.save(gestor);
	
	}
	
	/*
	 * public Cliente clienteLogin(String emailCliente, String password) throws
	 * ServiceException, NoSuchAlgorithmException, UnsupportedEncodingException {
	 * 
	 * String senhaCriptografada = Util.criptografarSenha(password); Cliente cliente
	 * = this.clienteDAO.clienteLogin(emailCliente, senhaCriptografada);
	 * 
	 * if (cliente == null) { throw new
	 * ServiceException("Login/senha não encontrados"); }
	 * 
	 * return cliente; }
	 */
	
	/*
	 * public void reEnviarEmailConfirmacao(String email) throws MessagingException
	 * {
	 * 
	 * Cliente cliente = this.findByEmail(email);
	 * 
	 * if (cliente.getAtivo() == false) {
	 * 
	 * this.emailService.enviarConfirmacaoDeConta(cliente);
	 * 
	 * }
	 */
	
	public Gestor editarPerfil(Gestor gestor,HttpSession session) {
		Gestor gestorLogado=(Gestor)session.getAttribute("gestorlogin");
		Gestor user = this.gestorDAO.findByid(gestorLogado.getIdGestor());
			
		user.setGestor(gestor.getGestor());
		user.setEmailGestor(gestor.getEmailGestor());
		user.setPassword(gestor.getPassword());
		user.setDataNascimento(gestor.getDataNascimento());
		user.setCPF(gestor.getCPF());
		user.setTelefone(gestor.getTelefone());
		user.setFotoGestor(gestor.getFotoGestor());
		
	
		return user;
	}

	
}
