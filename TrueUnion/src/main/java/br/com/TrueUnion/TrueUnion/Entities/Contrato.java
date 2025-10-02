package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "desc_contrato", nullable = true)
	private String desc;
	@Column(name = "valor_contrato")
	private double valor;
	@Column(name = "prazo_pagamento")
	private LocalDate prazoPagamento;
	@ManyToOne
	@JoinColumn(name = "id_evento")
	private Evento evento;
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;

	public Contrato(String desc, double valor, LocalDate prazoPagamento, Evento evento, Fornecedor fornecedor) {
		this.desc = desc;
		this.valor = valor;
		this.prazoPagamento = prazoPagamento;
		this.evento = evento;
		this.fornecedor = fornecedor;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getPrazoPagamento() {
		return prazoPagamento;
	}

	public void setPrazoPagamento(LocalDate prazoPagamento) {
		this.prazoPagamento = prazoPagamento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
