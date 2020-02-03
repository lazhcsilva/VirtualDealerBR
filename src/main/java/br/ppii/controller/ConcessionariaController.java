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

import br.ppii.model.Concessionaria;
import br.ppii.persistence.ConcessionariaDAO;
import br.ppii.service.ConcessionariaService;

@Controller
public class ConcessionariaController {
	
	@Autowired
	private ConcessionariaDAO concessionariaDAO;
	
	@Autowired
	private ConcessionariaService concessionariaService;

	@PostMapping("/salvarConcessionaria")
	public String salvarConcessionaria(@Valid Concessionaria concessionaria, BindingResult br, Model model, RedirectAttributes ra,Errors errors) {
		
		if(errors.hasErrors()) {
			
			ra.addFlashAttribute("mensagem", "erro");
			return this.Cadastro(concessionaria);
			
		} else {
			
			try {
				
				this.concessionariaService.salvarConcessionaria(concessionaria);
				
			} catch(ServiceException | MessagingException e) {
				
				ra.addFlashAttribute("menssage", "Não foi possível criar usuário: " + e.getMessage());
                ra.addFlashAttribute("cliente", concessionaria);
				return "redirect:/cadastrocliente";
				
			}
			
			ra.addFlashAttribute("menssage", "Conta criada com sucesso!");
			
		}
		
		return "redirect:/cadastroConcluido";
		
	}
	
	private String Cadastro(@Valid Concessionaria concessionaria) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping("/concessionariaLogin")
	public String efetuarLogin(HttpServletRequest request, @ModelAttribute Concessionaria concessionaria, @RequestParam(name = "retorno", required = false) String retorno, RedirectAttributes ra, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String redirect = "redirect:/index";
		if (retorno != null) {
			redirect = "redirect:" + retorno;
		}

		Concessionaria concessionariaLogada;
		try {
			concessionariaLogada = this.concessionariaService.concessionariaLogin(concessionaria.getEmailConcessionaria(), concessionaria.getPassword());
			session.setAttribute("concessionariaLogada", concessionariaLogada);
		} catch (ServiceException e) {
			ra.addFlashAttribute("mensagemErro", e.getMessage());

			return "redirect:/login";
		}

		ra.addFlashAttribute("loginEfetuado", true);
		return redirect;
	}
	
	@GetMapping("/editarConcessionaria")
	public String editarConcessionaria(Integer idConcessionaria, Model model) {
		model.addAttribute("lista", this.concessionariaDAO.findById(idConcessionaria));
		return "perfilconcessionaria";
	}
	
	@GetMapping("/apagarConcessionaria")
	public String apagarConcessionaria(Integer idConcessionaria) {
		this.concessionariaDAO.deleteById(idConcessionaria);
		return "index";
	}
}