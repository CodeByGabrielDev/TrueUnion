package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "eventos_donos") // table intermediaria
public class EventosDono {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "id_dono")
	private Usuario usu;
	@ManyToOne
	@JoinColumn(name = "id_evento")
	private Evento event;
	@Column(name = "data_evento")
	private LocalDateTime data;

	public EventosDono() {

	}

	public EventosDono(Usuario usu, LocalDateTime data) {
		super();
		this.usu = usu;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsu() {
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Evento getEvent() {
		return event;
	}

	public void setEvent(Evento event) {
		this.event = event;
	}
	
	

}
