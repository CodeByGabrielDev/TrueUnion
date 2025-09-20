package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "convidado")
public class Convidado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nome_convidado", nullable = true)
	private String nome;
	@Column(name = "email_convidado", nullable = true)
	private String email;
	@ManyToOne
	@JoinColumn(name = "statusRSVP_id")
	private StatusRSVP statusRSVP; // O DISPARO DE STATUS NO PERFIL DO CONVIDADO SÓ DEVE SER FEITO A PARTIR DE UM
									// METODO QUE DISPARA AUTOMATICAMENTE APÓS REALIZAR O INVITE PARA TODOS OS
									// CONVIDADOS, VAMOS FALANDO SOBRE
	@Column(name = "data_confirmacao")
	private LocalDateTime dataConfirmacao;
	// TENHO UMAS IDEIAS MANEIRAS PARA ALGUMAS REGRAS E MANTER A INTEGRIDADE DOS
	// DADOS NO BANCO COM ESSA DATA_CONFIRMACAO
	// AO REALIZAR O SETSTATUSRSVP PUXANDO PELO ID NO BANCO DE CONFRIMADO, TERA DE
	// AUTOMATICAMENTE TAMBEM DAR UM LOCALDATETIME.NOW() PARA LOG DE CONFIMRACAO,
	// LOGS SAO BEM VISTOS...
	// É LEGAL AS RN IMPLEMENTARMOS EM CONJUNTO, TENHO UMAS IDEIAS LEGAIS...
	@OneToMany(mappedBy = "convidado")
	private List<ConvidadosEmEventos> evento = new ArrayList<>();
	@Column(name = "telefone_convidado")
	private String telefone;
	
	
	public Convidado() {
		
	}
	public Convidado(String nome, String email, LocalDateTime dataConfirmacao) {
		this.nome = nome;
		this.email = email;
		this.dataConfirmacao = dataConfirmacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StatusRSVP getStatusRSVP() {
		return statusRSVP;
	}

	public void setStatusRSVP(StatusRSVP statusRSVP) {
		this.statusRSVP = statusRSVP;
	}

	public LocalDateTime getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(LocalDateTime dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	public List<ConvidadosEmEventos> getEvento() {
		return evento;
	}

	public void setEvento(List<ConvidadosEmEventos> evento) {
		this.evento = evento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
