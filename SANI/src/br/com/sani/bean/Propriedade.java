package br.com.sani.bean;

import java.text.ParseException;
import javax.swing.JOptionPane;

public class Propriedade {
	//private 
	
	//private String tipoPropriedade;
	private String propriedadeComercial;
	//private String terrenoComercial;
	//private String galpaoComercial;
	//private String salaComercial;
	//private String imovelParaRendaComercial;
	//private String predioComercial;
	private String propriedadeResidencial;
	//private String terrenoResidencial;
	//private String casaResidencial;
	//private String apartamentoResidencial;
	//private String sobradoResidencial;
	private String estadoImovel;
	//private String dispVenda;
	//private String vendido;
	//private String dispAluguel;
	//private String alugado;
	private String enderecoPropriedade;
	private String cepPropriedade;
	private String numeroPropriedade;
	private String complementoPropriedade;
	private String metragemPropriedade;
	
	private int idPropriedade;
	
	public Propriedade(){
		
	}
	
	//Métodos
	
	/*private String getTipoPropriedade() {
		return tipoPropriedade;
	}
	private void setTipoPropriedade(String tipoPropriedade) {
		this.tipoPropriedade = tipoPropriedade;
	}*/
	
	private String getPropriedadeComercial() {
		return propriedadeComercial;
	}
	public void setPropriedadeComercial(String propriedadeComercial) {
		this.propriedadeComercial = propriedadeComercial;
	}
	
	private String getPropriedadeResidencial() {
		return propriedadeResidencial;
	}
	public void setPropriedadeResidencial(String propriedadeResidencial) {
		this.propriedadeResidencial = propriedadeResidencial;
	}
	
	private String getEstadoImovel() {
		return estadoImovel;
	}
	public void setEstadoImovel(String estadoImovel) {
		this.estadoImovel = estadoImovel;
	}
	
	private String getEnderecoPropriedade() {
		return enderecoPropriedade;
	}
	public void setEnderecoPropriedade(String enderecoPropriedade) {
		this.enderecoPropriedade = enderecoPropriedade;
	}
	
	private String getCepPropriedade() {
		return cepPropriedade;
	}
	public void setCepPropriedade(String cepPropriedade) {
		this.cepPropriedade = cepPropriedade;
	}
	
	private String getNumeroPropriedade() {
		return numeroPropriedade;
	}
	public void setNumeroPropriedade(String numeroPropriedade) {
		this.numeroPropriedade = numeroPropriedade;
	}
	
	private String getComplementoPropriedade() {
		return complementoPropriedade;
	}
	public void setComplementoPropriedade(String complementoPropriedade) {
		this.complementoPropriedade = complementoPropriedade;
	}
	
	private String getMetragemPropriedade() {
		return metragemPropriedade;
	}
	public void setMetragemPropriedade(String metragemPropriedade) {
		this.metragemPropriedade = metragemPropriedade;
	}
	public void setIdPropriedade(int idPropriedade) {
		this.idPropriedade = idPropriedade;
	}
	public int getIdPropriedade() {
		return idPropriedade;
	}

}
