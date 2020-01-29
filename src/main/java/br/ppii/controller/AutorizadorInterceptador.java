package br.ppii.controller;

import org.springframework.web.servlet.HandlerInterceptor;

public class AutorizadorInterceptador implements HandlerInterceptor{

	private static final boolean CONTROLE_ACESSO = true;
	
	private static final String[] RECURSOS_LIVRES = {"/","/index", "/paginaInicial", "acesso/login", "/cadastro/cadastrocliente", 
			"/cadastro/cadastroconcessionaria", "acesso/gestorlogin"};
	
	private final String[] PAGINAS_ESTATICAS = {"/assets/"};
	
	private static final String ACESSO_NEGADO = "/acessonegado";
	
	private final String[] PAGINAS_USUARIO_LOGADO = {"/", "perfil/perfilusuario", "editar/editarcliente",
			"/editar/editargestor", "/editar/editaroferta"};
	
	private final String[] PAGINAS_CONCESSIONARIA_LOGADA = {"perfil/perfilconcessionaria", "/editar/editarempresa","/cadasro/cadastrooferta"};
	
	private final String[] PAGINAS_GESTOR_LOGADO = {"/perfil/gestor"};
	
	
}
