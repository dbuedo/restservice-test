package es.dvdbd.demo.restservice.call.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.dvdbd.demo.restservice.call.entity.Call;

@Repository
@Transactional
public class CallDaoImpl implements CallDao {
	
	@PersistenceContext
    private EntityManager manager;

	@Override
	public List<Call> getAll() {
		return manager.createQuery("SELECT c FROM Call c", Call.class).getResultList();
	}

	@Override
	public Call getById(Long id) {
		try {
			String ql = "SELECT c FROM Call c WHERE c.id = :id";
	        TypedQuery<Call> query = manager.createQuery(ql, Call.class);
	        query.setParameter("id", id);
	        return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long save(Call call) {
        manager.persist(call);
        return call.getId();
    }
}
