package br.com.sani.bean;

import java.util.Date;

public class ClienteCompradorJuridica {
	
	//private ClienteComprador codCliComprador;
	
	private String razaoSocial;
	private String cnpj;
	private String inscricaoEstadual;
	private Date dataFundacao;
	private String ramoAtividade;
	
	private ClienteComprador clienteComprador;
	
	//Getters & Setters
	
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
	
	public Date getDataFundacao() {
		return dataFundacao;
	}
	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	public String getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	/*public ClienteComprador getCodCliComprador() {
		return codCliComprador;
	}
	public void setCodCliComprador(ClienteComprador codCliComprador) {
		this.codCliComprador = codCliComprador;
	}*/
	public ClienteComprador getClienteComprador() {
		return clienteComprador;
	}
	public void setClienteComprador(ClienteComprador clienteComprador) {
		this.clienteComprador = clienteComprador;
	}
	
}