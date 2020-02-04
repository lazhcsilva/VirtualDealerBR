package br.ppii.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.ppii.exceptions.FotoInvalidaException;
import br.ppii.model.Foto;
import br.ppii.persistence.FotoDAO;

@Service
public class FotoStorageService {

	@Autowired
	FotoDAO fotoDAO;

	public Foto salvar(Foto foto) {  

		return this.fotoDAO.save(foto);

	}

	public Foto validar(MultipartFile file) throws FotoInvalidaException {

		if (!(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/gif") || file.getContentType().equals("image/png"))) {

			throw new FotoInvalidaException("Foto em formato inválido. Formatos permitidos: JPG, GIF, PNG.");

		} else if (file.getSize() > 5242880) {

			throw new FotoInvalidaException("O tamanho máximo da foto não deve exceder 5 MB.");

		} else {

			try {

				String nomeFoto = StringUtils.cleanPath(file.getOriginalFilename());
				String tipoFoto = file.getContentType();
				byte[] conteudoFoto = file.getBytes();

				return new Foto(nomeFoto, tipoFoto, null,conteudoFoto, null);

			} catch (IOException ioe) {

				throw new FotoInvalidaException("Desculpe, algo deu errado.");

			}
		}
	}

	public Foto obterPorId(String fotoId) {
		// TODO Auto-generated method stub
		return null;
	}

}
