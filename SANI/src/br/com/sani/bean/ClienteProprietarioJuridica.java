package br.com.sani.bean;

import java.util.Date;

public class ClienteProprietarioJuridica {
	
	private ClienteProprietario ClienteProprietario;
	
	private String razaoSocial;
	private String cnpj;
	private String inscEstadual;
	private Date dataFundacao;
	private String ramoAtividade;
	public ClienteProprietario getClienteProprietario() {
		return ClienteProprietario;
	}
	public void setClienteProprietario(ClienteProprietario clienteProprietario) {
		ClienteProprietario = clienteProprietario;
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
	public String getInscEstadual() {
		return inscEstadual;
	}
	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
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
	
	
	
	
}
