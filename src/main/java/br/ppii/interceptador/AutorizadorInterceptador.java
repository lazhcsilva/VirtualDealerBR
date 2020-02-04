package br.ppii.interceptador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import br.ppii.model.Cliente;
import br.ppii.model.Concessionaria;
import br.ppii.model.Gestor;

public class AutorizadorInterceptador implements HandlerInterceptor  {

	private final boolean CONTROLAR_ACESSO = true;
	
	private final String ACESSO_NEGADO = "acessonegado";
	
	private final String[] PAGINAS_ESTATICAS = {"/assets/"};
	
	private final String[] PAGINAS_DESLOGADO = {"/", "/acesso/login", "/facaparte", "/index", "/paginainicial", "/sobrenos", "/cadastro/cadastrocliente"};
	
	private final String[] PAGINAS_USUARIO_LOGADO = {"/editar/editarcliente", "/perfil/perfilcliente", ACESSO_NEGADO};
	
	private final String[] PAGINAS_CONCESSIONARIA_LOGADA = {"/perfil/perfilconcessionaria", "/cadastro/cadastrooferta", "/editar/editarempresa", ACESSO_NEGADO};
	
	private final String[] PAGINAS_GESTOR_LOGADO = {"/cadastro/cadastroplano", "/gestor/listarplanos", "/cadastro/cadastrarconcessionaria"};

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object controller) throws
			Exception {
		
		String urlRequisitada = request.getServletPath();
		
		Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");
		Concessionaria concessionariaLogada = (Concessionaria) request.getSession().getAttribute("concessionariaLogada");
		Gestor gestorLogado = (Gestor) request.getSession().getAttribute("gestorLogado");
		
		boolean estaLogado = clienteLogado != null ? true : false;
		boolean estaLogada = concessionariaLogada != null ? true : false;
		boolean gestorEstaLogado = gestorLogado != null ? true : false;
		
		if(!CONTROLAR_ACESSO) {
			
			return true;
			
		}
		
		if (urlRequisitada.contains("/gestor")) {
			
			if (estaLogado) {
				
//				if (clienteLogado.getAtivo == true) {
//				
//					return true;
//				
//				} else {
//				
//					request.getRequestDispatcher(ACESSO_NEGADO).forward(request, response);
//					return false;
//				
//				}
				
			} else {
				
				response.sendRedirect("/entrar?destino="+urlRequisitada);
				return false;
			
			}
			
		}
		
		for (String paginaLogado : PAGINAS_USUARIO_LOGADO) {
			
			if(urlRequisitada.contains(paginaLogado)) {
				
				if (estaLogado) {
					
					return true;
					
				} else {
					
					if (!urlRequisitada.equals("/") && !urlRequisitada.equals("/logout")) {
						
						response.sendRedirect("/login?destino=" + urlRequisitada);
						return false;
						
					} else {
						
						response.sendRedirect("/login");
						return false;
						
					}
					
				}
				
			}
			
		}
		
		for (String paginaLogado : PAGINAS_CONCESSIONARIA_LOGADA) {
			
			if(urlRequisitada.contains(paginaLogado)) {
				
				if (estaLogada) {
					
					return true;
					
				} else {
					
					if (!urlRequisitada.equals("/") && !urlRequisitada.equals("/logout")) {
						
						response.sendRedirect("/login?destino=" + urlRequisitada);
						return false;
						
					} else {
						
						response.sendRedirect("/login");
						return false;
						
					}
					
				}
				
			}
			
		}
		
		for (String paginaLogado : PAGINAS_GESTOR_LOGADO) {
			
			if(urlRequisitada.contains(paginaLogado)) {
				
				if (gestorEstaLogado) {
					
					return true;
					
				} else {
					
					if (!urlRequisitada.equals("/") && !urlRequisitada.equals("/logout")) {
						
						response.sendRedirect("/login?destino=" + urlRequisitada);
						return false;
						
					} else {
						
						response.sendRedirect("/login");
						return false;
						
					}
					
				}
				
			}
			
		}
		
		for (String paginaDeslogado : PAGINAS_DESLOGADO) {
			
			if (urlRequisitada.equals(paginaDeslogado)) {
				
				if (!estaLogado) {
					
					return true;
				
				} else {
					
					response.sendRedirect("/home");
					return false;
				
				}
			
			}
		
		}
		
		for (String paginaEstatica : PAGINAS_ESTATICAS) {
		
			if (urlRequisitada.contains(paginaEstatica)) {
			
				return true;
			
			}
		
		}
		
		return true;
		
	}
	
}