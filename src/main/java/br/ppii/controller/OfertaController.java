package br.ppii.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ppii.model.Oferta;
import br.ppii.persistence.OfertaDAO;
import br.ppii.service.OfertaService;

@Controller
public class OfertaController {

	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private OfertaDAO ofertaDAO;
	
	private static String caminhoImagens ="C:\\Users\\Alan\\git\\VirtualDealerBR\\src\\main\\resources\\static\\assets\\images\\Oferta\\";
	
	public Oferta findByPlaca(String placa) {
		return ofertaDAO.findByPlacaIgnoreCase(placa);
	}
	
	public Oferta findByChassi(String chassi) {
		return ofertaDAO.findByChassiIgnoreCase(chassi);
	}
	
	public Oferta findByRenavam(String renavam) {
		return ofertaDAO.findByRenavamIgnoreCase(renavam);
	}
	
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
	public String salvarPalestrante(@Valid Oferta oferta, @RequestParam("file") MultipartFile arquivo, RedirectAttributes ra ,BindingResult br) {
		
		if (this.findByPlaca(oferta.getPlaca()) != null) {
		
			throw new ServiceException("Já existe uma placa cadastrada");
		
		}
		
		if(this.findByChassi(oferta.getChassi()) != null) {
		
			throw new ServiceException("Já existe um chassi cadastrado");
		
		}
		
		if(this.findByRenavam(oferta.getRenavam()) != null) {
			
			throw new ServiceException("Este Renavam já foi cadastrado");
			
		}
		
		try {
			
			if(!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagens+String.valueOf(oferta.getIdOferta())+arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				
				oferta.setFotoOferta(String.valueOf(oferta.getIdOferta())+arquivo.getOriginalFilename());
			}
			
		} catch (Exception e) {
			
			ra.addFlashAttribute("menssage", "Não foi possível criar usuário: " + e.getMessage());
            ra.addFlashAttribute("oferta", oferta);
			return "redirect:/cadastro";
			
		}
		
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
