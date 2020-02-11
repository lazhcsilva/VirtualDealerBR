package br.ppii.interceptador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import br.ppii.model.Cliente;
import br.ppii.model.Concessionaria;
import br.ppii.model.Gestor;

public class AutorizadorInterceptador implements HandlerInterceptor  {

	private final boolean CONTROLAR_ACESSO = true;
	
	private static final String ACESSO_NEGADO = "/acessoNegado";
	
	private static final String[] RECURSOS_LIVRES = {"/index", "/paginaInicial", "/facaParte", "/sobreNos", "/ativarConta", "/cadastro", ACESSO_NEGADO};
	
	private final String[] RECURSOS_CLIENTE = {"/index", "/login", "/editarCliente", "/paginaInical", "/facaParte", "/planos", "/sobreNos", "/logout" , ACESSO_NEGADO};
	
	private final String[] RECURSOS_CONCESSIONARIA = {"/index", "/login", "/paginaInicial", "/editarConcessionaria", "/cadastrarOferta", "/editarOferta" , ACESSO_NEGADO};
	
	private final String[] RECURSOS_GESTOR = {"/index", "/gestoLogin", "/paginaInicial", "/facaParte", "/sobreNos", "/cadastroConcessionaria", "/cadastrarPlano", "/gestor", ACESSO_NEGADO};
	
	public boolean preHandler(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	
		if(handler instanceof ResourceHttpRequestHandler) {
		
			return true;
		
		}

		if (!CONTROLAR_ACESSO) {
		
			return true;
		
		}
		
		for (String recurso : RECURSOS_LIVRES) {
		
			if (request.getRequestURL().toString().endsWith(recurso)) {
		
				return true;
		
			}
		
		}
		
		if (request.getSession().getAttribute("clienteLogado") == null) {
		
			request.getRequestDispatcher(ACESSO_NEGADO).forward(request, response);
			return false;
		
		} else if (request.getSession().getAttribute("concessionariaLogada") == null) {
		
			request.getRequestDispatcher(ACESSO_NEGADO).forward(request, response);
			return false;
		
		} else if (request.getSession().getAttribute("gestorLogado") == null) {
			
			request.getRequestDispatcher(ACESSO_NEGADO).forward(request, response);
			return false;
			
		} else {
			
			Cliente cliente = (Cliente) request.getSession().getAttribute("clienteLogado");
			Concessionaria concessionaria = (Concessionaria) request.getSession().getAttribute("concessionariaLogada");
			Gestor gestor = (Gestor) request.getSession().getAttribute("gestorLogado");
			
			for (String recurso : RECURSOS_GESTOR) {
			
				if (request.getRequestURL().toString().contains(recurso) && gestor.getPermissao() == 2) {
				
					return true;
				
				}
			
			}
			
			for (String recurso : RECURSOS_CONCESSIONARIA) {
				
				if (request.getRequestURI().toString().contains(recurso) && concessionaria.getPermissao() == 1) {
					
					return true;
					
				}
				
			}
			
			for (String recurso : RECURSOS_CLIENTE) {
				
				if (request.getRequestURL().toString().contains(recurso) && cliente.getPermissao() == 0) {
				
					return true;
				
				}
			
			}
		
			request.getRequestDispatcher(ACESSO_NEGADO).forward(request, response);
			return false;
		}
		
	}

}