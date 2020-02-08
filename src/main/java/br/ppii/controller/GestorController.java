package br.ppii.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.ppii.model.Gestor;
import br.ppii.persistence.GestorDAO;
import br.ppii.service.GestorService;

@Controller
public class GestorController {
	
	@Autowired
	private GestorService gestorService;

	@Autowired
	private GestorDAO gestorDAO;
	
	@PostMapping("/gestorLogin")
	public String gestorLogin(HttpServletRequest request, Gestor gestorAtributos, @RequestParam(name = "retorno", required = false) String retorno, RedirectAttributes ra, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		Gestor gestorLogado;
		try {
			gestorLogado = this.gestorService.logarGestor(gestorAtributos.getEmailGestor(), gestorAtributos.getPassword());
			session.setAttribute("gestorLogado", gestorLogado);
			ra.addFlashAttribute("mensagem", "logado");
		} catch (ServiceException e) {
			ra.addFlashAttribute("mensagem", e.getMessage());
			System.out.println(e.getMessage());
			ra.addFlashAttribute("gestorAtributos", gestorAtributos);
			return "redirect:/gestorLogin";
		}

		ra.addFlashAttribute("loginEfetuado", true);
		return "redirect:/gestor";
	}
	
	@GetMapping("/editarGestor")
	public String editarGestor(Model model, Integer idGestor) {
		
		model.addAttribute("gestor", this.gestorDAO.findById(idGestor));
		return "";
		
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession sessao) {
		
		sessao.invalidate();
		return "redirect:/index";
	
	}
	
	@GetMapping("/editarPerfilGestor")
	public ModelAndView exibirEditarPerfil(HttpSession session,RedirectAttributes ra) {
		
		ModelAndView mv= new ModelAndView("editarperfil");
		if (session.getAttribute("gestorLogado")==null) {
			
			ra.addFlashAttribute("acessoNegado", true);
			ra.addFlashAttribute("retorno", "/editarPefilGestor");
		mv.setViewName("/redirect:/index");
		return mv;
		
		}
		Gestor gestor=(Gestor) session.getAttribute("gestorLogado");
		mv.addObject(gestor);
		return mv;
		
	}
	
	public void save(Gestor gestor) {
		
		this.gestorDAO.save(gestor);
	
	}
	
	@PostMapping("editarperfilGestor")
	public String editarPefil(@ModelAttribute Gestor gestor,RedirectAttributes ra,HttpSession session) {

		Gestor gestorSessao = (Gestor) session.getAttribute("usuarioLogado");
		gestor.setIdGestor(gestorSessao.getIdGestor());
		gestor = this.gestorService.editarPerfil(gestor,session);
		this.gestorService.save(gestor);
		session.setAttribute("usuarioLogado", gestor);
		ra.addFlashAttribute("sucesso", "Alteração realizada com sucesso");
		
		return"redirect:/editarPerfil";
	}
}