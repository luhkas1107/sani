package br.com.sani.bean;

public class ClienteProprietario {
	
	private int codCliProprietario;
	private String cep;
	private String numeroEndereco;
	private String complementoEndereco;
	private String telefone;
	private String celular;
	
	//Getters & Setters
	
	public int getCodCliProprietario() {
		return codCliProprietario;
	}
	public void setCodCliProprietario(int codCliProprietario) {
		this.codCliProprietario = codCliProprietario;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumeroEndereco() {
		return numeroEndereco;
	}
	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
}
