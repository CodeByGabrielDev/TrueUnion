package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDate;
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
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nome", nullable = false)
	private String nome;
	@Column(unique = true)
	private String cpf;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(name = "senha_usuario", nullable = false)
	private String senha;
	@Column(name = "telefone_usuario", nullable = true) // CAMPO NÃO OBRIGATORIO
	private String telefone;
	@Column(name = "data_cadastramento")
	private LocalDate dataCadastramento; // MEGA UTIL PARA LOGS DE SALVAMENTOS NO BANCO.
	private boolean inativo; // anteriormente era uma tabela com as infos q continha os status, mas pela
								// redução fica melhor assim em tipo booleano
	@OneToMany(mappedBy = "donoEvento")
	private List<Evento> evento = new ArrayList<>();

	public Usuario() {

	}

	public Usuario(String nome, String cpf, String email, String senha, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.dataCadastramento = LocalDate.now();

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataCadastramento() {
		return dataCadastramento;
	}

	public void setDataCadastramento(LocalDate dataCadastramento) {
		this.dataCadastramento = dataCadastramento;
	}

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public List<Evento> getEvento() {
		return evento;
	}

	public void setEvento(List<Evento> evento) {
		this.evento = evento;
	}

}
