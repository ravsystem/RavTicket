package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SubCategoria {
	
	@Id @GeneratedValue
	private Long in;
	private String descr;
	
	public SubCategoria() {}

	public Long getIn() {
		return in;
	}

	public void setIn(Long in) {
		this.in = in;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
