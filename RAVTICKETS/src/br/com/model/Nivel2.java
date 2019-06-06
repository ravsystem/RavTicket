package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Nivel2 extends Agente{
	
	public Nivel2(Long id, String nome, String senha, String tipo) {
		super(id, nome, senha, tipo);
	}
	
	public Nivel2() {}
	
	@Override
	public void TipoAgente() {
		this.setTipo("N2");
	}

}
