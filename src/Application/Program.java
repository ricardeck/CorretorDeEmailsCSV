package Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Entities.Domain;
import Entities.Email;
import java.io.FileWriter;
import java.io.IOException;

public class Program {
	private String localArquivoEmail = "email_list.csv";
	private String localArquivoDominio = "domain_list.csv";
	private ArrayList<Email> emails = new ArrayList<Email>();
	private ArrayList<Domain> domains = new ArrayList<Domain>();
	private double numeroCorrecoes;
	private double percenteCorrecoes;

	public String getLocalArquivoEmail() {
		return this.localArquivoEmail;
	}

	public void setLocalArquivoEmail(String localArquivoEmail) {
		this.localArquivoEmail = localArquivoEmail;
	}

	public void setLocalArquivoDominio(String localArquivoDominio) {
		this.localArquivoDominio = localArquivoDominio;
	}

	public String getLocalArquivoDominio() {
		return this.localArquivoDominio;
	}

	public double getNumeroCorrecoes() {
		return this.numeroCorrecoes;
	}

	public double getPercenteCorrecoes() {
		return this.percenteCorrecoes;
	}

	public void addEmail(String txtEmail) throws IOException {
		Email emailTxt = new Email(txtEmail);
		emails.add(emailTxt);
	}

	public void addDominio(String txtDominio) throws IOException {
		Domain dominioTxt = new Domain(txtDominio);
		domains.add(dominioTxt);
	}

	public void carregaCsv() throws IOException {
		try {
			File arquivoEmail = new File(localArquivoEmail);
			File arquivoDominio = new File(localArquivoDominio);
			Scanner leitorCliente = new Scanner(arquivoEmail);
			Scanner leitorDominio = new Scanner(arquivoDominio);
			String linhasDoArquivo = new String();
			// carrega os domínios.
			while (leitorCliente.hasNext()) {
				linhasDoArquivo = leitorCliente.nextLine();
				String[] valoresEntreEspacadores = linhasDoArquivo.split("'");
				Email email = new Email(valoresEntreEspacadores[1]);
				emails.add(email);
			}

			linhasDoArquivo = null;
			// carrega os emails.
			while (leitorDominio.hasNext()) {
				linhasDoArquivo = leitorDominio.nextLine();
				String[] valoresEntreVirgulas = linhasDoArquivo.split("'");
				Domain domain = new Domain(valoresEntreVirgulas[1]);
				domains.add(domain);
			}
			// Conta a quantidade de correções.
			for (Email e : emails) {
				if (!e.isCorrect(domains)) {
					numeroCorrecoes++;
				}
			}
			leitorCliente.close();
			leitorDominio.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	public void calculaPercentualCorrecoes() {
		if (this.numeroCorrecoes != 0 && this.emails.size() != 0) {
			percenteCorrecoes = (this.numeroCorrecoes / this.emails.size()) * 100;
		} else
			percenteCorrecoes = 0.0;
	}

	public void gravaCsv() throws IOException {
		// Salva os domínios.
		this.calculaPercentualCorrecoes();
		try {
			FileWriter myWriter = new FileWriter(localArquivoDominio);

			for (Domain d : domains) {
				myWriter.write("'" + d + "'");
				myWriter.append('\n');

			}
			myWriter.flush();
			domains = new ArrayList<Domain>();
			myWriter.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		// Salva os emails.
		try {
			FileWriter myWriter = new FileWriter(localArquivoEmail);
			for (Email e : emails) {
				myWriter.write("'" + e + "'");
				myWriter.append('\n');
			}
			myWriter.flush();
			emails = new ArrayList<Email>();
			myWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
}
