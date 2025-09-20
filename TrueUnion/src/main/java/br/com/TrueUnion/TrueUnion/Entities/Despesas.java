package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDate;

import br.com.TrueUnion.TrueUnion.Enums.CategoriaDespesa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "despesas")
public class Despesas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "categoria",nullable = false)
	private CategoriaDespesa categoria;
	@Column(name = "valor",nullable = false)
	private double valor;
	@Column(name = "data_despesa")
	private LocalDate data;
	@ManyToOne
	@JoinColumn(name = "id_forma_pgto") // REFERENCIA DIRETAMENTE QUAL FORMA DE PAGAMENTO DESEJADA //CARTAO //DINHEIRO 							// // BOLETO // FIADO
	private FormaPgtos formaPgto;
	@ManyToOne
	@JoinColumn(name = "id_evento")
	private Evento event;
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;
	@Column(name = "desc_despesa", nullable = true)
	private String desc;
	
	
	public Despesas() {
		
	}
	public Despesas(CategoriaDespesa categoria, double valor, FormaPgtos formaPgto, Evento event, Fornecedor fornecedor,
			String desc) {
		super();
		this.categoria = categoria;
		this.valor = valor;
		this.formaPgto = formaPgto;
		this.event = event;
		this.fornecedor = fornecedor;
		this.desc = desc;
		this.data = LocalDate.now(); // ao realizar a instancia ja salva a data feita
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CategoriaDespesa getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDespesa categoria) {
		this.categoria = categoria;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public FormaPgtos getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgtos formaPgto) {
		this.formaPgto = formaPgto;
	}

	public Evento getEvent() {
		return event;
	}

	public void setEvent(Evento event) {
		this.event = event;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
