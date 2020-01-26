package br.ppii.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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
	
}
