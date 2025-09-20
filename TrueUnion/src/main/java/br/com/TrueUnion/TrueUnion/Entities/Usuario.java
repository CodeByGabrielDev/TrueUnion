package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDate;

import br.com.TrueUnion.TrueUnion.Enums.PerfilUsuario;
import br.com.TrueUnion.TrueUnion.Enums.StatusUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nome", nullable = false)
	private String nome;
	@Column(nullable = false)
	private String email;
	@Column(name = "senha_usuario", nullable = false)
	private String senha;
	@Column(name = "perfil_usuario", nullable = false)
	private PerfilUsuario perfil;
	@Column(name = "telefone_usuario", nullable = true) // CAMPO NÃO OBRIGATORIO
	private String telefone;
	@Column(name = "data_cadastramento")
	private LocalDate dataCadastramento; // MEGA UTIL PARA LOGS DE SALVAMENTOS NO BANCO.
	@Column
	private StatusUsuario status;

	public Usuario() {

	}

	public Usuario(String nome, String email, String senha, PerfilUsuario perfil, String telefone, StatusUsuario status) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.perfil = perfil;
		this.telefone = telefone;
		this.dataCadastramento = LocalDate.now();
		this.status = status;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
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

	public StatusUsuario getStatus() {
		return status;
	}

	public void setStatus(StatusUsuario status) {
		this.status = status;
	}

	

}
