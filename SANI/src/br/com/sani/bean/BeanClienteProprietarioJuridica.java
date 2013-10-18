package br.com.sani.bean;

public class BeanClienteProprietarioJuridica {
	
	private ClienteProprietario codCliPropietario;
	
	private String razaoSocial;
	private String cnpj;
	private String inscEstadual;
	private String dataFundacao;
	private String ramoAtividade;
	
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
	public String getInscEstadual() {
		return inscEstadual;
	}
	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
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
	public ClienteProprietario getCodCliPropietario() {
		return codCliPropietario;
	}
	public void setCodCliPropietario(ClienteProprietario codCliPropietario) {
		this.codCliPropietario = codCliPropietario;
	}
	
}
