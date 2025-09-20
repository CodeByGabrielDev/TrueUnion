package br.com.TrueUnion.TrueUnion.Entities;

import java.util.List;

import br.com.TrueUnion.TrueUnion.Enums.CategoriaDespesa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nome_fornecedor")
	private String nome;
	@Column
	private CategoriaDespesa categoria;
	@Column(name = "telefone_fornecedor")
	private String telefone;
	@Column(name = "email_fornecedor")
	private String email;
	@Column(name = "endereco_fornecedor")
	private String endereco;
	@Column(name = "nota_fornecedor")
	private double notaFornecedor;
	@OneToMany(mappedBy = "fornecedor")
	private List<Despesas> despesas;

	public Fornecedor() {

	}

	public Fornecedor(String nome, CategoriaDespesa categoria, String telefone, String email, String endereco,
			double notaFornecedor) {
		super();
		this.nome = nome;
		this.categoria = categoria;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.notaFornecedor = notaFornecedor;
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

	public CategoriaDespesa getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDespesa categoria) {
		this.categoria = categoria;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public double getNotaFornecedor() {
		return notaFornecedor;
	}

	public void setNotaFornecedor(double notaFornecedor) {
		this.notaFornecedor = notaFornecedor;
	}

	public List<Despesas> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesas> despesas) {
		this.despesas = despesas;
	}
	
	

}
