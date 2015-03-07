package es.dvdbd.demo.restservice.call.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	@SuppressWarnings("unchecked")
	public Call getById(Long id) {
		String hql = "SELECT c FROM Call c where id=" + id;
        Query query = manager.createQuery(hql);
        
        List<Call> listCall = (List<Call>) query.getResultList();
   
        if (listCall != null && !listCall.isEmpty()) {
            return listCall.get(0);
        }         
        return null;
	}

	@Override
	public Long save(Call call) {
        manager.persist(call);
        return call.getId();
    }
}
