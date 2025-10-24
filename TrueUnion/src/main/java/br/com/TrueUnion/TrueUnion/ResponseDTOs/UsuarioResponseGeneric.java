package br.com.TrueUnion.TrueUnion.ResponseDTOs;


public class UsuarioResponseGeneric {
	private int id;
	private String name;
	private String email;

	public UsuarioResponseGeneric(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
