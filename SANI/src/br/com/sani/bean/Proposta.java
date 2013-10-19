package br.com.sani.bean;

public class Proposta {
	
	private int codProposta;
	private String valorProposta;
	private String validadeProposta;
	private String estadoProposta;
	private String statusProposta;
	
	private ClienteComprador codCliComprador;
	private Imovel codImovel;
	
	//Getters & Setters
	
	public int getCodProposta() {
		return codProposta;
	}
	public void setCodProposta(int codProposta) {
		this.codProposta = codProposta;
	}
	public String getValorProposta() {
		return valorProposta;
	}
	public void setValorProposta(String valorProposta) {
		this.valorProposta = valorProposta;
	}
	public String getValidadeProposta() {
		return validadeProposta;
	}
	public void setValidadeProposta(String validadeProposta) {
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
	public ClienteComprador getCodCliComprador() {
		return codCliComprador;
	}
	public void setCodCliComprador(ClienteComprador codCliComprador) {
		this.codCliComprador = codCliComprador;
	}
	public Imovel getCodImovel() {
		return codImovel;
	}
	public void setCodImovel(Imovel codImovel) {
		this.codImovel = codImovel;
	}

}
