package es.dvdbd.demo.restservice.call.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Call {
	
	@Id @GeneratedValue
	private Long id;
	private String caller;
	
	public Call() {
		
	}
	
	public Call(Long id, String caller) {
		super();
		this.id = id;
		this.caller = caller;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	
	@Override
	public String toString() {
		return "Call [id=" + id + ", caller=" + caller + "]";
	}
	
	
}
