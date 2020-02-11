package br.ppii.controller;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ppii.model.Concessionaria;
import br.ppii.persistence.ConcessionariaDAO;
import br.ppii.service.ConcessionariaService;

@Controller
public class ConcessionariaController {
	
	private static String caminhoImagens ="C:\\Users\\Alan\\git\\VirtualDealerBR\\src\\main\\resources\\static\\assets\\images\\Concessionaria\\";
	
	@Autowired
	private ConcessionariaDAO concessionariaDAO;
	
	@Autowired
	private ConcessionariaService concessionariaService;
	
	@GetMapping("/perfilConcessionaria")
	public String perfilConcessionaria(Concessionaria concessionaria, Model model, HttpSession session) {
		
		return "/perfil/perfilconcessionaria";
		
	}

	@PostMapping("/salvarConcessionaria")
	public String salvarConcessionaria(@Valid Concessionaria concessionaria, BindingResult br, Model model, RedirectAttributes ra,Errors errors, @RequestParam("file") MultipartFile arquivo) throws Exception{
		
		if(errors.hasErrors()) {
			
			ra.addFlashAttribute("mensagem", "erro");
			return this.cadastroEmpresa(concessionaria);
			
		} else {
			
			try {
				
				if(!arquivo.isEmpty()) {
					byte[] bytes = arquivo.getBytes();
					Path caminho = Paths.get(caminhoImagens+String.valueOf(concessionaria.getIdConcessionaria())+arquivo.getOriginalFilename());
					Files.write(caminho, bytes);
					
					concessionaria.setFotoConcessionaria(String.valueOf(concessionaria.getIdConcessionaria())+arquivo.getOriginalFilename());
				}
				
				this.concessionariaService.salvarConcessionaria(concessionaria);

				
			} catch(ServiceException | MessagingException e) {
				
				ra.addFlashAttribute("menssage", "Não foi possível criar conta concessionaria: " + e.getMessage());
                ra.addFlashAttribute("concessionaria", concessionaria);
				return "redirect:/cadastroEmpresa";
				
			}
			
			ra.addFlashAttribute("menssage", "Conta criada com sucesso!");
			
		}
		
		return "redirect:/perfilConcessionaria";
		
	}
	
	@GetMapping("/cadastroEmpresa")
	public String cadastroEmpresa(Concessionaria concessionaria) {
		return "cadastro/cadastroconcessionaria";
	}

	@PostMapping("/concessionariaLogin")
	public String concessionariaLogin(HttpServletRequest request, @ModelAttribute Concessionaria concessionaria, @RequestParam(name = "retorno", required = false) String retorno, RedirectAttributes ra, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		Concessionaria concessionariaLogada;
		
		try {
			
			concessionariaLogada = this.concessionariaService.logarConcessionaria(concessionaria.getEmailConcessionaria(), concessionaria.getPassword());
			session.setAttribute("concessionariaLogada", concessionariaLogada);
			
		} catch (ServiceException e) {
			
			ra.addFlashAttribute("mensagemErro", e.getMessage());
			return "paginaInicial";
		
		}
		
		return "redirect:/paginaInicial";
		
	}
	
	@GetMapping("/editarConcessionaria")
	public String editarConcessionaria(Integer idConcessionaria, Model model, HttpSession session) {
		model.addAttribute("lista", this.concessionariaDAO.findById(idConcessionaria));
		return "perfilconcessionaria";
	}
	
	@GetMapping("/apagarConcessionaria")
	public String apagarConcessionaria(Integer idConcessionaria) {
		this.concessionariaDAO.deleteById(idConcessionaria);
		return "index";
	}

}