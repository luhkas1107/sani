package br.com.sani.bean;

public class ClienteComprador {
	
	private int codCliComprador;
	private String cep;
	private String numeroEndereco;
	private String complementoEndereco;
	
	private String telefone;
	private String celular;
	private String tpCliente;
	
	private ClienteCompradorFisica clienteCompradorFisica;
	private ClienteCompradorJuridica clienteCompradorJuridica;
	
	//Getters & Setters
	
	
	
	public ClienteCompradorFisica getClienteCompradorFisica() {
		return clienteCompradorFisica;
	}
	public String getTpCliente() {
		return tpCliente;
	}
	public void setTpCliente(String tpCliente) {
		this.tpCliente = tpCliente;
	}
	public void setClienteCompradorFisica(
			ClienteCompradorFisica clienteCompradorFisica) {
		this.clienteCompradorFisica = clienteCompradorFisica;
	}
	public ClienteCompradorJuridica getClienteCompradorJuridica() {
		return clienteCompradorJuridica;
	}
	public void setClienteCompradorJuridica(
			ClienteCompradorJuridica clienteCompradorJuridica) {
		this.clienteCompradorJuridica = clienteCompradorJuridica;
	}
	public int getCodCliComprador() {
		return codCliComprador;
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
