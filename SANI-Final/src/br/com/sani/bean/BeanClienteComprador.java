package br.com.sani.bean;

public class BeanClienteComprador {
	
	private int codCliComprador;
	private String cep;
	private String numeroEndereco;
	private String complementoEndereco;
	
	//Getters & Setters
	
	public int getCodCliComprador() {
		return codCliComprador;
	}
	public void setCodCliComprador(int codCliComprador) {
		this.codCliComprador = codCliComprador;
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
		
}
