package br.com.TrueUnion.TrueUnion.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "forma_pgto")
public class FormaPgtos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "desc_programador")
	private String descPagamento;
	@Column(name = "tipo_pagamento")
	private String tipoPagamento;
	@OneToMany(mappedBy = "formaPgto")
	private List<Despesas> despesas = new ArrayList<>();

	public FormaPgtos() {

	}

	public FormaPgtos(String descPagamento, String tipoPagamento) {
		super();
		this.descPagamento = descPagamento;
		this.tipoPagamento = tipoPagamento;
	}

	public String getDescPagamento() {
		return descPagamento;
	}

	public void setDescPagamento(String descPagamento) {
		this.descPagamento = descPagamento;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

}
