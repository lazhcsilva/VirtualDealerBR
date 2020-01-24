package br.ppii.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

	public static String criptografarSenha(String password)  throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		 String senhaCriptografada = null;
			
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));
			 
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			
			senhaCriptografada = hexString.toString();	
			algorithm.reset();
			
			return senhaCriptografada;
			
		}
	
}
