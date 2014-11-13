package br.com.repository3d.repository;

import java.util.List;

import javax.ejb.Stateless;

import br.com.repository.entidades.Categoria;
import br.com.repository.entidades.Objeto;

@Stateless
@SuppressWarnings("unchecked")
public class ObjetoRepository extends Repository<Objeto, Long> {


	public List<Objeto> getAllDaCategoria(Categoria categoria) {
		return getEntityManager()
		.createNativeQuery("SELECT O.* FROM TB_OBJETO O WHERE O.FK_CATEGORIA= :idCategoria", Objeto.class)
		.setParameter("idCategoria", categoria.getIdCategoria())
		.getResultList();
	}

	public List<Objeto> getAllOrderByNome() {
		return getEntityManager()
		.createNativeQuery("SELECT O.* FROM TB_OBJETO O ORDER BY O.DS_NOME", Objeto.class)
		.getResultList();
	}

	public List<Objeto> getAllObjetosPorIdCategoria(Long idCategoria) {
		return getEntityManager()
				.createNativeQuery("SELECT * FROM tb_objeto where FK_CATEGORIA= :idCategoria", Objeto.class)
				.setParameter("idCategoria", idCategoria)
				.getResultList();
	}

	public Objeto getObjetoPorID(Long idObjeto) {
		return (Objeto) getEntityManager()
				.createNativeQuery("SELECT * FROM tb_objeto where ID_OBJETO= :idObjeto", Objeto.class)
				.setParameter("idObjeto", idObjeto)
				.getSingleResult();
	}

}
