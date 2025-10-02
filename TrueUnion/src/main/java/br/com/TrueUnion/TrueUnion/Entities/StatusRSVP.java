package br.com.TrueUnion.TrueUnion.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "status_RSVP")
public class StatusRSVP {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_RSVP;
	@OneToMany(mappedBy = "statusRSVP")
	private List<Convidado> convidado; // DEPOIS VALIDAR NO BANCO OS STATUS QUE EXISTEM, SERÃO QUATRO NO TOTAL,
										// CASO TENHA UM CONVIDADO QUE ACEITOU, DEVE REALIZAR O SET STATUS DO CONVIDADO
										// PARAO ID DE STATUSRSVP QUE SEJA RESPECTIVO AO CONFIRMADO!
	@Column(name = "descProgramador")
	private String descProgramador;

	public StatusRSVP() {

	}

	public StatusRSVP(List<Convidado> convidado, String descProgramador) {
		super();
		this.convidado = convidado;
		this.descProgramador = descProgramador;
	}

	public int getId_RSVP() {
		return id_RSVP;
	}

	public void setId_RSVP(int id_RSVP) {
		this.id_RSVP = id_RSVP;
	}

	public List<Convidado> getConvidado() {
		return convidado;
	}

	public void setConvidado(List<Convidado> convidado) {
		this.convidado = convidado;
	}

	public String getDescProgramador() {
		return descProgramador;
	}

	public void setDescProgramador(String descProgramador) {
		this.descProgramador = descProgramador;
	}

}
