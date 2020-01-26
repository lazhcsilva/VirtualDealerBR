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
import br.ppii.service.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
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
				return "redirect:/exibirFormParticipante";
				
			}
			
			ra.addFlashAttribute("menssage", "Conta criada com sucesso!");
			
		}
		
		return "redirect:/perfil";
		
	}
	
	@GetMapping("/cadastro")
	public String Cadastro(Cliente cliente) {
		return "cadastro/cadastrocliente";
	}
	
	@PostMapping("/clienteLogin")
	public String efetuarLogin(HttpServletRequest request, @ModelAttribute Cliente cliente, @RequestParam(name = "retorno", required = false) String retorno, RedirectAttributes ra, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String redirect = "redirect:/index";
		if (retorno != null) {
			redirect = "redirect:" + retorno;
		}

		Cliente clienteLogado;
		try {
			clienteLogado = this.clienteService.clienteLogin(cliente.getEmailCliente(), cliente.getPassword());
			session.setAttribute("usuarioLogado", clienteLogado);
		} catch (ServiceException e) {
			ra.addFlashAttribute("mensagemErro", e.getMessage());

			return "redirect:/perfilCliente";
		}

		ra.addFlashAttribute("loginEfetuado", true);
		return redirect;
	}
	
}
