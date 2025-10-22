package br.com.TrueUnion.TrueUnion.ResponseDTOs;


import java.time.LocalDate;

import br.com.TrueUnion.TrueUnion.Entities.Evento;

public class TarefaResponse {

	private int id;
	private String desc;
	private LocalDate prazo;
	private Evento evento;
	private boolean atrasada;

	public TarefaResponse(String desc, LocalDate prazo, Evento evento,boolean atrasada) {
		super();
		this.desc = desc;
		this.prazo = prazo;
		this.evento = evento;
		this.atrasada = atrasada;
	}
	
	
	public boolean isAtrasada() {
		return atrasada;
	}


	public void setAtrasada(boolean atrasada) {
		this.atrasada = atrasada;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
