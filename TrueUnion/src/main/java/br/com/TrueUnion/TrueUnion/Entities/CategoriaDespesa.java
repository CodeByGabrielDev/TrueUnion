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
@Table(name = "Categoria_despesa")
public class CategoriaDespesa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String desc_programador;
	@OneToMany(mappedBy = "categoria")
	private List<Despesas> despesa;

	public CategoriaDespesa(int id, String desc_programador, List<Despesas> despesa) {
		super();
		this.id = id;
		this.desc_programador = desc_programador;
		this.despesa = despesa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc_programador() {
		return desc_programador;
	}

	public void setDesc_programador(String desc_programador) {
		this.desc_programador = desc_programador;
	}

	public List<Despesas> getDespesa() {
		return despesa;
	}

	public void setDespesa(List<Despesas> despesa) {
		this.despesa = despesa;
	}

}
