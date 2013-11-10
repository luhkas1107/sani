package br.com.sani.bean;

public class ClienteProprietario {
	
	private int codCliProprietario;
	private String cep;
	private String numeroEndereco;
	private String complementoEndereco;
	private String telefone;
	private String celular;
	private String tpCliente;
	
	private ClienteProprietarioFisica clienteProprietarioFisica;
	private ClienteProprietarioJuridica clienteProprietarioJuridica;
	
	//Getters & Setters
	
	public ClienteProprietarioFisica getClienteProprietarioFisica() {
		return clienteProprietarioFisica;
	}
	public String getTpCliente() {
		return tpCliente;
	}
	public void setTpCliente(String tpCliente) {
		this.tpCliente = tpCliente;
	}
	public void setClienteProprietarioFisica(
			ClienteProprietarioFisica clienteProprietarioFisica) {
		this.clienteProprietarioFisica = clienteProprietarioFisica;
	}
	public ClienteProprietarioJuridica getClienteProprietarioJuridica() {
		return clienteProprietarioJuridica;
	}
	public void setClienteProprietarioJuridica(
			ClienteProprietarioJuridica clienteProprietarioJuridica) {
		this.clienteProprietarioJuridica = clienteProprietarioJuridica;
	}
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
