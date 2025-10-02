package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pagamento {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "valor_pago")
	private double valor;
	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;
	@ManyToOne
	@JoinColumn(name = "id_pagamento")
	private FormaPgtos formaPagamento;
	
	
}
