package br.ppii.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.ppii.model.Oferta;
import br.ppii.persistence.OfertaDAO;
import br.ppii.service.OfertaService;

@Controller
public class OfertaController {

	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private OfertaDAO ofertaDAO;
	
	@GetMapping("/cadastroOferta")
	public String cadastroOferta(Oferta oferta, Model model) {
		return "cadastro/cadastrooferta";
	}
	
	@GetMapping("/listarOferta")
	public String exibirLista(Model model) {
		model.addAttribute("lista", this.ofertaService.listarTodos(Sort.by("veiculo")));
		return "redirect:/paginaInicial";
	}
	
	@PostMapping("/salvarOferta")
	public String salvarPalestrante(@Valid Oferta oferta, BindingResult br) {
		this.ofertaService.save(oferta);
		return "redirect:/paginaInicial";
	}
	
	@GetMapping("/editarOferta")
	public String editarPalestrante(Model model, Integer idOferta) {
		 model.addAttribute("oferta", this.ofertaDAO.findById(idOferta));
		 return "editar/editaroferta";
		 
	}
	
	@GetMapping("/removerOferta")
	public String removerOferta(Integer idOferta) {
		this.ofertaService.remover(idOferta);
		return "redirect:/listarPalestrante";
	}


	
}
