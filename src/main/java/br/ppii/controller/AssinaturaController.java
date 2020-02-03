package br.ppii.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ppii.model.Assinatura;
import br.ppii.service.AssinaturaService;

@Controller
public class AssinaturaController {

	@Autowired
	private AssinaturaService assinaturaService;
	
	@PostMapping("/salvarAssinatura")
	public String salvarCliente(@Valid Assinatura assinatura, BindingResult br, Model model, RedirectAttributes ra,Errors errors) {
		
		this.assinaturaService.novaAssinatura(assinatura);

		return "redirect:/cadastro/cadastroplano";
		
	}
	
	@GetMapping("/planos")
	public String exibirLista(Model model) {
		model.addAttribute("lista", this.assinaturaService.listarTodos(Sort.by("idAssinatura")));
		return "planos";
	}

	@GetMapping("/cadastrarPlano")
	public String exibirForm(Assinatura assinatura) {
		return "cadastro/cadastroplano";
	}
	
	@GetMapping("/editarAssinatura")
	public String editarSala(Integer idAssinatura, Model model) {
		model.addAttribute("assinatura", this.assinaturaService.obterPorId(idAssinatura));
		return "redirect:/cadastro/cadastroplano";
	}
	
	@GetMapping("/removerAssinatura")
	public String removerAssinatura(Integer idAssinatura) {
		this.assinaturaService.remover(idAssinatura);
		return "redirect:/planos";
	}
	
}
