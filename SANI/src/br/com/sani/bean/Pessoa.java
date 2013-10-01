package br.com.sani.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public abstract class Pessoa {
	private String nome;
	private String sexo;
	private String cpf;
	private String rg;
	private String estadoCivil;
	private String nacionalidade;
	private String endereco;
	private String numeroEndereco;
	private String complementoEndereco;
	private String cep;	
	private String telefoneResidencial;
	private String telefoneCelular;
	private String emailPessoal;	
	private Date dataNascimento;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// Métodos
	
	public Pessoa(String data){
		try {
			this.dataNascimento = sdf.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getNome() {
		return nome;
	}
	private void setNome(String nome) {
		if (nome.equals("") || nome.equals(null)) {
			JOptionPane.showMessageDialog(null, "Campo Nome OBRIGATÓRIO!");
		} else {
			this.nome = nome;
		}
	}
	
	private String getSexo() {
		return sexo;
	}
	private void setSexo(String sexo) {
		if (sexo.equals("") || sexo.equals(null)) {
			JOptionPane.showMessageDialog(null, "Campo SEXO OBRIGATÓRIO!");
		} else {
			this.sexo= sexo;
		}
	}
		
	private String getCpf() {
		return cpf;
	}
	private void setCpf(String cpf) {
		if (cpf.equals("") || cpf.equals(null)){
			JOptionPane.showMessageDialog(null, "Campo CPF OBRIGATÓRIO");
		} else{
			this.cpf = cpf;
		  }
	}
	
	private String getRg() {
		return rg;
	}
	private void setRg(String rg) {
		this.rg = rg;
	}

	private String getEstadoCivil() {
		return estadoCivil;
	}
	private void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	private String getNacionalidade() {
		return nacionalidade;
	}
	private void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	private String getEndereco() {
		return endereco;
	}
	private void setEndereco(String endereco) {
		if (endereco.equals("") || endereco.equals(null)){
			JOptionPane.showMessageDialog(null, "Campo Endereço OBRIGATÓRIO");
		} else{
			this.endereco = endereco;
		  }
	}

	private String getNumeroEndereco() {
		return numeroEndereco;
	}
	private void setNumeroEndereco(String numeroEndereco) {
		if (numeroEndereco.equals("") || numeroEndereco.equals(null)) {
			JOptionPane.showMessageDialog(null, "Campo Número do Endereço OBRIGATÓRIO!");
		} else {
			this.numeroEndereco = numeroEndereco;
		}
	}
	
	private String getComplementoEndereco() {
		return complementoEndereco;
	}
	private void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	
	private String getCep() {
		return cep;
	}
	private void setCep(String cep) {
		if (cep.equals("") || cep.equals(null)){
			JOptionPane.showMessageDialog(null, "Campo CEP OBRIGATÓRIO");
		} else{
			this.cep = cep;
		  }
	}
	
	private String getTelefoneResidencial() {
		return telefoneResidencial;
	}
	private void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	
	private String getTelefoneCelular() {
		return telefoneCelular;
	}
	private void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	
	private String getEmailPessoal() {
		return emailPessoal;
	}
	private void setEmailPessoal(String emailPessoal) {
		if (emailPessoal.equals("") || emailPessoal.equals(null)){
			JOptionPane.showMessageDialog(null, "Campo Email OBRIGATÓRIO");
		} else{
			this.emailPessoal = emailPessoal;
		  }
	}
	
	private Date getDataNascimento() {
		return dataNascimento;
	}
	private void setDataNascimento(Date dataNascimento) {
		if (dataNascimento.equals("") || dataNascimento.equals(null)){
			JOptionPane.showMessageDialog(null, "Campo Data de Nascimento OBRIGATÓRIO");
		} else{
			this.dataNascimento = dataNascimento;
		  }
	}
}