package br.com.sani.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class ClienteComprador {

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
	
	private int id;
	private String renda;
	private String siteClienteComprador;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public ClienteComprador(String data){
		try {
			this.dataNascimento = sdf.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ClienteComprador(){
		
	}
	
	//Métodos Pessoa
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if (nome.equals("") || nome.equals(null)) {
			JOptionPane.showMessageDialog(null, "Campo Nome OBRIGATÓRIO!");
		} else {
			this.nome = nome;
		}
	}
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		if (sexo.equals("") || sexo.equals(null)) {
			JOptionPane.showMessageDialog(null, "Campo SEXO OBRIGATÓRIO!");
		} else {
			this.sexo= sexo;
		}
	}
		
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		if (cpf.equals("") || cpf.equals(null)){
			JOptionPane.showMessageDialog(null, "Campo CPF OBRIGATÓRIO");
		} else{
			this.cpf = cpf;
		  }
	}
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		if (endereco.equals("") || endereco.equals(null)){
			JOptionPane.showMessageDialog(null, "Campo Endereço OBRIGATÓRIO");
		} else{
			this.endereco = endereco;
		  }
	}

	public String getNumeroEndereco() {
		return numeroEndereco;
	}
	public void setNumeroEndereco(String numeroEndereco) {
		if (numeroEndereco.equals("") || numeroEndereco.equals(null)) {
			JOptionPane.showMessageDialog(null, "Campo Número do Endereço OBRIGATÓRIO!");
		} else {
			this.numeroEndereco = numeroEndereco;
		}
	}
	
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		if (cep.equals("") || cep.equals(null)){
			JOptionPane.showMessageDialog(null, "Campo CEP OBRIGATÓRIO");
		} else{
			this.cep = cep;
		  }
	}
	
	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}
	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	
	public String getEmailPessoal() {
		return emailPessoal;
	}
	public void setEmailPessoal(String emailPessoal) {
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
	
	//Métodos ClienteComprador
	
	public String getRenda() {
		return renda;
	}
	public void setRenda(String renda) {
		if (renda.equals("") || renda.equals(null)) {
			JOptionPane.showMessageDialog(null, "Campo Renda OBRIGATÓRIO!");
		} else {
			this.renda = renda;
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setSiteClienteComprador(String siteClienteComprador) {
		this.siteClienteComprador = siteClienteComprador;
	}

	public String getSiteClienteComprador() {
		return siteClienteComprador;
	}
}
