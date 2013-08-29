package br.com.sani.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class Funcionario {

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
	private String cargoFuncionario;
	private String loginFuncionario;
	private String senhaFuncionario;
	private String confirmaSenhaFuncionario;
	private String siteFuncionario;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Funcionario(String data){
		try {
			this.dataNascimento = sdf.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Métodos Pessoa
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

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
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		if (dataNascimento.equals("") || dataNascimento.equals(null)){
			JOptionPane.showMessageDialog(null, "Campo Data de Nascimento OBRIGATÓRIO");
		} else{
			this.dataNascimento = dataNascimento;
		  }
	}
	
	//Métodos Funcionario
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCargoFuncionario() {
		return cargoFuncionario;
	}
	public void setCargoFuncionario(String cargoFuncionario) {
		if (cargoFuncionario.equals("") || cargoFuncionario.equals(null)) {
			JOptionPane.showMessageDialog(null, "Campo Cargo OBRIGATÓRIO!");
		} else {
			this.cargoFuncionario = cargoFuncionario;
		}
	}
	
	public String getLoginFuncionario() {
		return loginFuncionario;
	}
	public void setLoginFuncionario(String loginFuncionario) {
		if (loginFuncionario.equals("") || loginFuncionario.equals(null)) {
			JOptionPane.showMessageDialog(null, "Campo Login OBRIGATÓRIO!");
		} else {
			this.loginFuncionario = loginFuncionario;
		}
	}
	
	public String getSenhaFuncionario() {
		return senhaFuncionario;
	}
	public void setSenhaFuncionario(String senhaFuncionario) {
		if (senhaFuncionario.equals("") || senhaFuncionario.equals(null)) {
			JOptionPane.showMessageDialog(null, "Campo Senha OBRIGATÓRIO!");
		} else {
			this.senhaFuncionario = senhaFuncionario;
		}
	}
	
	private String getConfirmaSenhaFuncionario() {
		return confirmaSenhaFuncionario;
	}
	public void setConfirmaSenhaFuncionario(String confirmaSenhaFuncionario) {
		if (confirmaSenhaFuncionario.equals("") || confirmaSenhaFuncionario.equals(null)) {
			JOptionPane.showMessageDialog(null, "Campo Confirme a Senha OBRIGATÓRIO!");
		} else {
			this.confirmaSenhaFuncionario = confirmaSenhaFuncionario;
		}
	}

	public void setSiteFuncionario(String siteFuncionario) {
		this.siteFuncionario = siteFuncionario;
	}

	public String getSiteFuncionario() {
		return siteFuncionario;
	}
}
