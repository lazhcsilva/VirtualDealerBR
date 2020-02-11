package br.ppii.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImagemController {

	private static String caminhoImagem ="C:\\Users\\Alan\\git\\VirtualDealerBR\\src\\main\\resources\\static\\assets\\images\\Cliente\\";
	private static String caminhoImagemOferta ="C:\\Users\\Alan\\git\\VirtualDealerBR\\src\\main\\resources\\static\\assets\\images\\Oferta\\";
	private static String caminhoImagemConcessionaria ="C:\\Users\\Alan\\git\\VirtualDealerBR\\src\\main\\resources\\static\\assets\\images\\Concessionaria\\";
	
	@GetMapping("/mostrarImagemCliente/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem")String imagem) throws IOException {
		
		File imagemArquivoFile = new File(caminhoImagem+imagem);
		
		if(imagem!=null || imagem.trim().length()>0) {
		
			return Files.readAllBytes(imagemArquivoFile.toPath());
		
		}
		
		return Files.readAllBytes(imagemArquivoFile.toPath());
	
	}
	
	@GetMapping("/mostrarImagemOferta/{imagem}")
	@ResponseBody
	public byte[] retornarImagem2(@PathVariable("imagem")String imagem) throws IOException {
		
		File imagemArquivoFile = new File(caminhoImagemOferta+imagem);
		
		if(imagem!=null || imagem.trim().length()>0) {
	
			return Files.readAllBytes(imagemArquivoFile.toPath());
		
		}
		
		return Files.readAllBytes(imagemArquivoFile.toPath());
	
	}
	
	@GetMapping("/mostrarImagemConcessionaria/{imagem}")
	@ResponseBody
	public byte[] retornarImagem3(@PathVariable("imagem")String imagem) throws IOException {
		
		File imagemArquivoFile = new File(caminhoImagemConcessionaria+imagem);
		
		if(imagem!=null || imagem.trim().length()>0) {
	
			return Files.readAllBytes(imagemArquivoFile.toPath());
		
		}
		
		return Files.readAllBytes(imagemArquivoFile.toPath());
	
	}
	
}