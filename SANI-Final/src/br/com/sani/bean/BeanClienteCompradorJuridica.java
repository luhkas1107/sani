package br.com.sani.bean;

public class BeanClienteCompradorJuridica extends BeanClienteComprador {
	
	private int codCliComprador;
	private String razaoSocial;
	private String cnpj;
	private String inscricaoEstadual;
	private String dataFundacao;
	private String ramoAtividade;
	
	//Getters & Setters
	
	public int getCodCliComprador() {
		return codCliComprador;
	}
	public void setCodCliComprador(int codCliComprador) {
		this.codCliComprador = codCliComprador;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public String getDataFundacao() {
		return dataFundacao;
	}
	public void setDataFundacao(String dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	public String getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	
}