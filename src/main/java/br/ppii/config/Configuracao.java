package br.ppii.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.ppii.interceptador.AutorizadorInterceptador;

@Configuration
public class Configuracao implements WebMvcConfigurer {
/*	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(new AutorizadorInterceptador());
	
	}
*/	
}
