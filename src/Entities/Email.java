package Entities;

import java.util.ArrayList;

public class Email {
	private String email;
	private String nome;
	private String dominio;
	private String dominioModificado;
	private boolean isDomainCorrect;

	public Email(String email) {
		super();
		String[] nomeProvedor = email.split("@");
		nome = nomeProvedor[0];
		dominio = nomeProvedor[1];
		this.email = this.nome + this.dominio;
	}

	public boolean isCorrect(ArrayList<Domain> domains) {
		this.removeAt();
		String nome = null;
		double percent = 0.0;
		for (Domain d : domains) {
			if (d.getNome().equals(this.dominio)) {
				this.isDomainCorrect = true;
				return this.isDomainCorrect;
			} else {
				if (this.percentEqual(d) > percent) {
					percent = this.percentEqual(d);
					nome = d.getNome();
				} else if (this.percentEqual(d) == percent) {
					nome = (nome.length() < d.getNome().length()) ? nome : d.getNome();
				}
			}
		}
		this.dominioModificado = nome;
		this.isDomainCorrect = false;
		return this.isDomainCorrect;
	}

	public void addAt() {
		if (this.dominio.indexOf("@") == -1) {
			this.dominio = "@" + this.dominio;
			this.email = nome + dominio;
		}

	}

	public void removeAt() {
		if (this.dominio.indexOf("@") > -1) {
			dominio = this.dominio.replace("@", "");
			this.email = nome + dominio;
		}

	}

	public double percentEqual(Domain domain) {
		double count = 0;
		ArrayList<Integer> positionIgnore = new ArrayList<Integer>();
		int tamThis = this.dominio.length();
		int tamDom = domain.getNome().length();
		for (int i = 0; i < tamThis; i++) {
			for (int j = 0; j < tamDom; j++) {
				if (Character.compare(this.dominio.charAt(i), domain.getNome().charAt(j)) == 0) {
					if (positionIgnore.contains(j)) {
					} else {
						count++;
						positionIgnore.add(j);
						break;
					}
				}
			}
		}
		return (count / domain.getNome().length()) * 100;
	}

	public void setDominio() {
		if (!this.isDomainCorrect) {
			this.dominio = this.dominioModificado;
			this.email = nome + dominio;
		}
	}

	@Override
	public String toString() {
		this.setDominio();
		this.addAt();
		return email;
	}

}
