package br.com.repository3d.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.repository.entidades.Categoria;
import br.com.repository3d.repository.CategoriaRepository;

@Stateless
public class CategoriaService {

	@EJB
	public CategoriaRepository categoriaRepository;
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.salvar(categoria);
	}
	
	public List<Categoria> getAllRaizes() {
		return categoriaRepository.getAllRaizes();
	}
}
