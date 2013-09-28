package br.com.sani.bean;

public class BeanClienteComprador {
	
	private int codCliComprador;
	private String cep;
	private String numeroEndereco;
	private String complementoEndereco;
	private String renda;
	private String profissao;
	
	//Getters & Setters
	
	public int getCodCliComprador(int i) {
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
	public String getRenda() {
		return renda;
	}
	public void setRenda(String renda) {
		this.renda = renda;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

}
