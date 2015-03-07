package es.dvdbd.demo.restservice.call.persistence;

import java.util.List;

import es.dvdbd.demo.restservice.call.entity.Call;

public interface CallDao {

	public List<Call> getAll();
	
	public Call getById(Long id);
	
	public Long save(Call call);
	
}
