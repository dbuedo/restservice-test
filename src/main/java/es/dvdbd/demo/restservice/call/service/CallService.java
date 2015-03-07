package es.dvdbd.demo.restservice.call.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import es.dvdbd.demo.restservice.call.entity.Call;

@Service
public class CallService {
	private final static Logger logger = LoggerFactory.getLogger(CallService.class);
	
	public List<Call> findAll() {
		ArrayList<Call> calls = new ArrayList<Call>();
		calls.add(new Call(1L, "Jose"));
		calls.add(new Call(2L, "Juan"));
		calls.add(new Call(3L, "Pedro"));
		return calls;
	}
	
	public Call findById(Long id) {
	    return new Call(1L, "Jose");
	}
	
}
