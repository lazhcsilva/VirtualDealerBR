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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.ppii.model.Cliente;
import br.ppii.persistence.ClienteDAO;
import br.ppii.service.ClienteService;

@Controller
public class ClienteController {
	
	private static String caminhoImagens ="C:\\Users\\Alan\\git\\VirtualDealerBR\\src\\main\\resources\\static\\assets\\images\\Cliente\\";

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@PostMapping("/salvarCliente")
	public String salvarCliente(@Valid Cliente cliente, BindingResult br, Model model, RedirectAttributes ra,Errors errors, @RequestParam("file") MultipartFile arquivo) throws Exception {
		
		if(errors.hasErrors()) {
			
			ra.addFlashAttribute("mensagem", "erro");
			return this.Cadastro(cliente);
			
		} else {
			
			try {
				
				if(!arquivo.isEmpty()) {
					byte[] bytes = arquivo.getBytes();
					Path caminho = Paths.get(caminhoImagens+String.valueOf(cliente.getIdCliente())+arquivo.getOriginalFilename());
					Files.write(caminho, bytes);
					
					cliente.setFotoCliente(String.valueOf(cliente.getIdCliente())+arquivo.getOriginalFilename());
				}
				
				this.clienteService.criarCliente(cliente);

				
			} catch(ServiceException | MessagingException e) {
				
				ra.addFlashAttribute("menssage", "Não foi possível criar usuário: " + e.getMessage());
                ra.addFlashAttribute("cliente", cliente);
				return "redirect:/cadastro";
				
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
			return "paginainicial";
		}
		
		return "paginainicial";
	}
	
	@GetMapping("/editarCliente")
	public String editarPalestrante(Model model, Integer idCliente) {
		 model.addAttribute("cliente", this.clienteDAO.findById(idCliente));
		 return "editar/editarcliente";
		 
	}
	
	
	@PostMapping("fotoCliente")
	public String editarPefil(@ModelAttribute Cliente cliente,RedirectAttributes ra,HttpSession session) {

		Cliente clienteSessao = (Cliente) session.getAttribute("usuarioLogado");
		cliente.setIdCliente(clienteSessao.getIdCliente());
		cliente = this.clienteService.editarPerfil(cliente,session);
		this.clienteService.save(cliente);
		session.setAttribute("usuarioLogado", cliente);
		ra.addFlashAttribute("sucesso", "Alteração realizada com sucesso");
		
		return"redirect:/editarPerfil";
	}
	
	
	
	
	@GetMapping("/editarPerfil")
	public ModelAndView exibirEditarPerfil(HttpSession session,RedirectAttributes ra) {
		
		ModelAndView mv= new ModelAndView("editar-perfil");
		if (session.getAttribute("clienteLogado")==null) {
			
			ra.addFlashAttribute("acessoNegado", true);
			ra.addFlashAttribute("retorno", "/editarPefil");
		mv.setViewName("/redirect:/Login");
		return mv;
		
		}
		Cliente cliente=(Cliente) session.getAttribute("clienteLogado");
		mv.addObject(cliente);
		return mv;
		
		
	}@PostMapping("cliente")
	public String editarPefilCliente(@ModelAttribute Cliente cliente,RedirectAttributes ra,HttpSession session) {

		Cliente usuarioSessao = (Cliente) session.getAttribute("clienteLogado");
		cliente.setIdCliente(usuarioSessao.getIdCliente());
		cliente = this.clienteService.editarPerfil(cliente,session);
		this.clienteService.save(cliente);
		session.setAttribute("clienteLogado", cliente);
		ra.addFlashAttribute("sucesso", "Alteração realizada com sucesso");
		
		return"redirect:/editarPerfil";
	}
	
	/*
	 * @GetMapping("/editarPerfil") public ModelAndView
	 * exibirEditarPerfil(HttpSession session,RedirectAttributes ra) {
	 * 
	 * ModelAndView mv= new ModelAndView("editar-perfil"); if
	 * (session.getAttribute("usuarioLogado")==null) {
	 * 
	 * ra.addFlashAttribute("acessoNegado", true); ra.addFlashAttribute("retorno",
	 * "/editarPefil"); mv.setViewName("/redirect:/participanteLogin"); return mv;
	 * 
	 * } Usuario usuario=(Usuario) session.getAttribute("usuarioLogado");
	 * mv.addObject(usuario); return mv; }
	 */
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "index";
		
	}
	
	@GetMapping("ativar")
	public String ativeSuaConta() {
		return "/ativarConta";
	}
	
}