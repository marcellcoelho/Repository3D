package br.com.repository3d.repository;

import java.util.List;

import javax.ejb.Stateless;

import br.com.repository.entidades.Categoria;

@Stateless
@SuppressWarnings("unchecked")
public class CategoriaRepository extends Repository<Categoria, Long> {

	public List<Categoria> getAllRaizes() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tb_categoria WHERE FK_CATEGORIA_PAI IS NULL");
		return getEntityManager().createNativeQuery(sql.toString(), Categoria.class).getResultList();
	}

}
