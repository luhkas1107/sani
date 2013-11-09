package br.com.sani.bean;

import java.util.Date;

public class ImagemImovel {
	
	private int idImagem;
	private int propriedade;
	private byte[] imagem;
	private Date dataImagem;
	
	
	public int getIdImagem() {
		return idImagem;
	}
	public void setIdImagem(int idImagem) {
		this.idImagem = idImagem;
	}
	public int getPropriedade() {
		return propriedade;
	}
	public void setPropriedade(int propriedade) {
		this.propriedade = propriedade;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public Date getDataImagem() {
		return dataImagem;
	}
	public void setDataImagem(Date dataImagem) {
		this.dataImagem = dataImagem;
	}
	
	
	
	
	
}
