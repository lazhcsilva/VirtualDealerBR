package br.ppii.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ppii.model.Cliente;
import br.ppii.persistence.ClienteDAO;
import br.ppii.service.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@PostMapping("/salvarCliente")
	public String salvarCliente(@Valid Cliente cliente, BindingResult br, Model model, RedirectAttributes ra,Errors errors) {
		
		if(errors.hasErrors()) {
			
			ra.addFlashAttribute("mensagem", "erro");
			return this.Cadastro(cliente);
			
		} else {
			
			try {
				
				this.clienteService.criarCliente(cliente);
				
			} catch(ServiceException | MessagingException e) {
				
				ra.addFlashAttribute("menssage", "Não foi possível criar usuário: " + e.getMessage());
                ra.addFlashAttribute("cliente", cliente);
				return "redirect:/cadastrocliente";
				
			}
			
			ra.addFlashAttribute("menssage", "Conta criada com sucesso!");
			
		}
		
		return "redirect:/cadastroConcluido";
		
	}
	
	@GetMapping("/cadastro")
	public String Cadastro(Cliente cliente) {
		return "cadastro/cadastrocliente";
	}
	
	@GetMapping("/perfilCliente")
	public String perfil(Model model, Cliente cliente) {
		
		return "perfil/perfilcliente";
	}
	
	@PostMapping("/clienteLogin")
	public String efetuarLogin(HttpServletRequest request, @ModelAttribute Cliente cliente, @RequestParam(name = "retorno", required = false) String retorno, RedirectAttributes ra, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		Cliente clienteLogado;
		try {
			clienteLogado = this.clienteService.clienteLogin(cliente.getEmailCliente(), cliente.getPassword());
			session.setAttribute("clienteLogado", clienteLogado);
		} catch (ServiceException e) {
			ra.addFlashAttribute("mensagemErro", e.getMessage());
		}
		
		return "paginainicial";
	}
	
	@GetMapping("/editarCliente")
	public String editarPalestrante(Model model, Integer idCliente) {
		 model.addAttribute("cliente", this.clienteDAO.findById(idCliente));
		 return "editar/editarcliente";
		 
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "index";
		
	}
	
}