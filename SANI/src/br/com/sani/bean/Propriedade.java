package br.com.sani.bean;


public class Propriedade {
	
	private int codPropriedade;
	private ClienteProprietario clienteProprietario;
	private String cep;
	private int numeroEndereco;
	private String complementoEndereco;
	private String tipoPropriedade;
	private String situacaoPropriedade;
	private float metragem;
	private float valorPropriedade;
	private Endereco endereco;
	
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public float getValorPropriedade() {
		return valorPropriedade;
	}
	public void setValorPropriedade(float valorPropriedade) {
		this.valorPropriedade = valorPropriedade;
	}
	public int getCodPropriedade() {
		return codPropriedade;
	}
	public void setCodPropriedade(int codPropriedade) {
		this.codPropriedade = codPropriedade;
	}
	public ClienteProprietario getClienteProprietario() {
		return clienteProprietario;
	}
	public void setClienteProprietario(ClienteProprietario clienteProprietario) {
		this.clienteProprietario = clienteProprietario;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public int getNumeroEndereco() {
		return numeroEndereco;
	}
	public void setNumeroEndereco(int numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	public String getTipoPropriedade() {
		return tipoPropriedade;
	}
	public void setTipoPropriedade(String tipoPropriedade) {
		this.tipoPropriedade = tipoPropriedade;
	}
	public String getSituacaoPropriedade() {
		return situacaoPropriedade;
	}
	public void setSituacaoPropriedade(String situacaoPropriedade) {
		this.situacaoPropriedade = situacaoPropriedade;
	}
	public float getMetragem() {
		return metragem;
	}
	public void setMetragem(float metragem) {
		this.metragem = metragem;
	}
	
	
	

}
