package br.com.repository3d.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.repository.entidades.Objeto;
import br.com.repository3d.repository.ObjetoRepository;

@Stateless
public class ObjetoService {
	
	@EJB
	private ObjetoRepository objetoRepository;
	
	public Objeto salvar(Objeto objeto) {
		return objetoRepository.salvar(objeto);
	}

	public List<Objeto> getAll() {
		return objetoRepository.getAll(Objeto.class);
	}

}
