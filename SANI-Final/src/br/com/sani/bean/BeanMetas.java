package br.com.sani.bean;

public class BeanMetas {
	
	private String dataInicio;
	private String dataFinal;
	private String valorMeta;
	
	private BeanCorretor codCorretor;
	
	//Getters & Setters
	
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getValorMeta() {
		return valorMeta;
	}
	public void setValorMeta(String valorMeta) {
		this.valorMeta = valorMeta;
	}
	public BeanCorretor getCodCorretor() {
		return codCorretor;
	}
	public void setCodCorretor(BeanCorretor codCorretor) {
		this.codCorretor = codCorretor;
	}	

}
