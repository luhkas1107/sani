package br.com.sani.bean;

import java.util.Date;

public class Proposta {
	
	private int codProposta;
	private int codCliComprador;
	private int codPropriedade;
	private Double valorProposta;
	private Date validadeProposta;
	private String estadoProposta;
	private String statusProposta;
	private String formaPagamento;
	private Date dataInicialProposta;
	private Date dataFinalProposta;
	private String tipoProposta;
	
	private ClienteComprador clienteComprador;
	private Propriedade propriedade;
	
	
	public int getCodProposta() {
		return codProposta;
	}
	public void setCodProposta(int codProposta) {
		this.codProposta = codProposta;
	}
	public int getCodCliComprador() {
		return codCliComprador;
	}
	public void setCodCliComprador(int codCliComprador) {
		this.codCliComprador = codCliComprador;
	}
	public int getCodPropriedade() {
		return codPropriedade;
	}
	public void setCodPropriedade(int codPropriedade) {
		this.codPropriedade = codPropriedade;
	}
	public Double getValorProposta() {
		return valorProposta;
	}
	public void setValorProposta(double d) {
		this.valorProposta = d;
	}
	public Date getValidadeProposta() {
		return validadeProposta;
	}
	public void setValidadeProposta(Date validadeProposta) {
		this.validadeProposta = validadeProposta;
	}
	public String getEstadoProposta() {
		return estadoProposta;
	}
	public void setEstadoProposta(String estadoProposta) {
		this.estadoProposta = estadoProposta;
	}
	public String getStatusProposta() {
		return statusProposta;
	}
	public void setStatusProposta(String statusProposta) {
		this.statusProposta = statusProposta;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Date getDataProposta() {
		return dataInicialProposta;
	}
	public void setDataProposta(Date dataProposta) {
		this.dataInicialProposta = dataProposta;
	}
	
	public Date getDataFinalProposta() {
		return dataFinalProposta;
	}
	public void setDataFinalProposta(Date dataProposta) {
		this.dataFinalProposta = dataProposta;
	}
	
	public String getTipoProposta() {
		return tipoProposta;
	}
	public void setTipoProposta(String tipoProposta) {
		this.tipoProposta = tipoProposta;
	}
	public ClienteComprador getClienteComprador() {
		return clienteComprador;
	}
	public void setClienteComprador(ClienteComprador clienteComprador) {
		this.clienteComprador = clienteComprador;
	}
	public Propriedade getPropriedade() {
		return propriedade;
	}
	public void setPropriedade(Propriedade propriedade) {
		this.propriedade = propriedade;
	}
	
	
}
