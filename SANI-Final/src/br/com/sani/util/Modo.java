package br.com.sani.util;

public enum Modo {
	
	SALVAR(0), EDITAR(1), PESQUISA(2);
	
	public final int valor;
	
	Modo(int valorOpcao){
        valor = valorOpcao;
    }
    public int getValor(){
        return valor;
    }



}
