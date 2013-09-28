package br.com.sani.bean;

public class BeanVenda {
	
	private int codTransacao;
	private String dataTransacao;
	private int	numeroContrado;
	private String arqContrato;
	
	//Getters & Setters
	
	public int getCodTransacao() {
		return codTransacao;
	}
	public void setCodTransacao(int codTransacao) {
		this.codTransacao = codTransacao;
	}
	public String getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(String dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public int getNumeroContrado() {
		return numeroContrado;
	}
	public void setNumeroContrado(int numeroContrado) {
		this.numeroContrado = numeroContrado;
	}
	public String getArqContrato() {
		return arqContrato;
	}
	public void setArqContrato(String arqContrato) {
		this.arqContrato = arqContrato;
	}

}
