package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SubCategoria {
	
	@Id @GeneratedValue
	private Long id;
	private String descr;
	private String Categoria;
	
	public SubCategoria() {}

	public Long getIn() {
		return id;
	}

	public void setIn(Long in) {
		this.id = in;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}
	
}
