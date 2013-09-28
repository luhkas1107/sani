package br.com.sani.bean;

public class BeanImovel {
	
	private int codImovel;
	private String cep;
	private String numeroEndereco;
	private String complementoEndereco;
	private String tipoImovel;
	private String statusImovel;
	private String metragemImovel;
	
	private Endereco endereco;
	private BeanCorretor corretor;
	private BeanClienteProprietario cliProprietario;
	
	//Getters & Setters
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public BeanCorretor getCorretor() {
		return corretor;
	}
	public void setCorretor(BeanCorretor corretor) {
		this.corretor = corretor;
	}
	public BeanClienteProprietario getCliProprietario() {
		return cliProprietario;
	}
	public void setCliProprietario(BeanClienteProprietario cliProprietario) {
		this.cliProprietario = cliProprietario;
	}
	public int getCodImovel() {
		return codImovel;
	}
	public void setCodImovel(int codImovel) {
		this.codImovel = codImovel;
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
	public String getTipoImovel() {
		return tipoImovel;
	}
	public void setTipoImovel(String tipoImovel) {
		this.tipoImovel = tipoImovel;
	}
	public String getStatusImovel() {
		return statusImovel;
	}
	public void setStatusImovel(String statusImovel) {
		this.statusImovel = statusImovel;
	}
	public String getMetragemImovel() {
		return metragemImovel;
	}
	public void setMetragemImovel(String metragemImovel) {
		this.metragemImovel = metragemImovel;
	}	

}
