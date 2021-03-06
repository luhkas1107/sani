package br.com.sani.bean;

import java.util.Date;

public class ClienteCompradorFisica {
	
	//PELO AMOR DO SANTO NOME DO PAI ETERNO DEUS, CAMPO DATA TEM O TIPO DE DADO "DATE", AMEM
		
	private String nome;
	private String rg;
	private String cpf;
	private Date dataNascimento;
	private Date dataFalecimento;
	private String sexo;
	private String estadoCivil;
	private Double renda;
	private String profissao;
	private String email;
	
	private ClienteComprador clienteComprador; // classe pai, ao inv�s de estender eu crio uma variavel com get e set

	//Getters & Setters
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataFalecimento() {
		return dataFalecimento;
	}

	public void setDataFalecimento(Date dataFalecimento) {
		this.dataFalecimento = dataFalecimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Double getRenda() {
		return renda;
	}

	public void setRenda(Double renda) {
		this.renda = renda;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	
	public ClienteComprador getClienteComprador() {
		return clienteComprador;
	}

	public void setClienteComprador(ClienteComprador clienteComprador) {
		this.clienteComprador = clienteComprador;
	}
	
	
	
}
