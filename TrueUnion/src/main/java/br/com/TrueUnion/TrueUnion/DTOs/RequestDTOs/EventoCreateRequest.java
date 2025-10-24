package br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import br.com.TrueUnion.TrueUnion.Entities.ConvidadosEmEventos;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EventoCreateRequest {

	@NotBlank(message = "O campo nome não pode estar vazio")
	private String nome;
	@NotNull
	private Double orcamento;
	@NotBlank
	private String local;
	@NotNull
	private LocalDate dataInicio;
	@NotNull
	private LocalDate dataFim;
	@NotBlank
	private String descricao;

	public EventoCreateRequest() {
		super();
	}

	public EventoCreateRequest(String nome, Double orcamento, String local, LocalDate dataInicio, LocalDate dataFim,
			String descricao) {
		super();
		this.nome = nome;
		this.orcamento = orcamento;
		this.local = local;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Double orcamento) {
		this.orcamento = orcamento;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
