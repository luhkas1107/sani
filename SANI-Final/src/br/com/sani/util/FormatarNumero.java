package br.com.sani.util;

public class FormatarNumero {
	
	public static String formatNumero(int numDigitos, int numero) {
		return String.format("%0" + numDigitos  + "d", numero);
	}

}
