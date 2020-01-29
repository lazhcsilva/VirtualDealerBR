package br.ppii.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.ppii.model.Cliente;
import br.ppii.model.Concessionaria;
import br.ppii.model.Endereco;
import br.ppii.model.Gestor;
import br.ppii.model.Oferta;
import br.ppii.service.OfertaService;

@Controller
public class MenuController {

	@Autowired
	private OfertaService ofertaService;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/paginaInicial")
	public String paginaInicial(Model model, Oferta oferta) {
		model.addAttribute("lista", this.ofertaService.listarTodos(Sort.by("veiculo")));
		return "paginainicial";
	}

	@GetMapping("/cadastroConcluido")
	public String Cadastroefetuadocomsucesso(Cliente cliente, Endereco endereco) {
		return "cad.concluidocomsucess";
	}
	@GetMapping("/pesquisa")
	public String pesquisa() {
		return "pesquisa";
	}
	
	@GetMapping("/login")
	public String login(Cliente cliente, Model model, HttpSession session) {
		model.addAttribute("cliente", new Cliente());
		return "acesso/login";
	}
	
	@GetMapping("/sobreNos")
	public String sobreNos() {
		return "sobrenos";
	}
	
	@GetMapping("/cadastroEmpresa")
	public String cadastroEmpresa(Concessionaria concessionaria, Endereco endereco, Model model, HttpSession session) {
		return "cadastro/cadastroconcessionaria";
	}
	
	@GetMapping("/editarPerfil")
	public String editarPerfil() {
		return "editarperfil";
	}
	
	@GetMapping("/empresas")
	public String empresas() {
		return "empresas";
	}
	
	@GetMapping("/pagamento")
	public String pagamento() {
		return "pagamento";
	}
	
	@GetMapping("/planos")
	public String planos(Model model) {
		return "planos";
	}
	
	@GetMapping("/trocaSenha")
	public String trocaSenha() {
		return "senha";
	}
	
	/*
	 * @GetMapping("/cadastroConcluido") public String cadastroConcluido() { return
	 * "cadastroconcluido"; }
	 */
	
	@GetMapping("/termos")
	public String termos() {
		return "termosdeuso";
	}
	
	@GetMapping("/descricaoOferta")
	public String descricaoOferta(Model model, Oferta oferta) {
		model.addAttribute("lista", this.ofertaService.listarTodos(Sort.by("veiculo")));
		return "acesso/descricaooferta";
	}
	
	@GetMapping("/gestor")
	public String gestor(Gestor gestor, HttpSession session, Model model) {
		return "perfil/gestor";
	}
	
	@GetMapping("/gestorLogin")
	public String loginGestor(Gestor gestor, HttpSession session){
		return "acesso/gestorlogin";
	}
	
	@GetMapping("/cadastrarPlano")
	public String cadastrarPlano() {
		return "cadastro/cadastrarplano";
	}
	
}