package br.com.sani.bean;

import java.util.Date;


public class Metas {

	private int idMeta;
	private String descrMeta;
	private Date dataInicio;
	private Date dataFim;
	private String stMeta;
	
	
	public String getStMeta() {
		return stMeta;
	}
	public void setStMeta(String stMeta) {
		this.stMeta = stMeta;
	}
	public int getIdMeta() {
		return idMeta;
	}
	public void setIdMeta(int idMeta) {
		this.idMeta = idMeta;
	}
	public String getDescrMeta() {
		return descrMeta;
	}
	public void setDescrMeta(String descrMeta) {
		this.descrMeta = descrMeta;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
	
}
