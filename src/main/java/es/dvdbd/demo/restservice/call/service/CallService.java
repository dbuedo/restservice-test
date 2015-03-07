package es.dvdbd.demo.restservice.call.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.dvdbd.demo.restservice.call.entity.Call;
import es.dvdbd.demo.restservice.call.persistence.CallDao;

@Service
public class CallService {
	private final static Logger logger = LoggerFactory.getLogger(CallService.class);
	
	@Autowired
	private CallDao callDao;
	
	public List<Call> findAll() {
		return callDao.getAll();
	}
	
	public Call findById(Long id) {
		return callDao.getById(id);
	}
	
}
