package br.com.solyos.showcase.arquillian.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CrudService {

	@PersistenceContext(unitName = "test")
	protected EntityManager em;

	public <T> void save(T t) {
		em.merge(t);
	}

	public void delete(Object t) {
		em.remove(em.merge(t));
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public <T> T findById(Object id, Class<T> type) {
		return (T) em.find(type, id);
	}

}
