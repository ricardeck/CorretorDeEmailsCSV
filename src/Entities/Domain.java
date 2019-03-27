package Entities;

public class Domain {
	private String nome;

	public Domain(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	@Override
	public String toString() {

		return nome;
	}
}