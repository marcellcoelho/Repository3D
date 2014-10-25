package br.com.repository3d.repository;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class Repository<T, I extends Serializable> {

	@PersistenceContext
	private EntityManager em;
	
	public T salvar(T entity) {
		return getEntityManager().merge(entity);
	}

	
	public void remover(T entity) {
			getEntityManager().remove(entity);
	}

	public void removerById(Class<T> classe, I pk) {
		getEntityManager().remove(getById(classe, pk));
	}

	public T getById(Class<T> classe, I pk) {
		try {
			return getEntityManager().find(classe, pk);
		} catch (NoResultException e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> classe) {
		return getEntityManager().createQuery("SELECT O FROM " + classe.getSimpleName() + " O").getResultList();
	}

	public EntityManager getEntityManager() {
		return em;
	}
}
