package br.com.sani.bean;

public class BeanClienteProprietarioFisica {
	
	private BeanClienteProprietario codCliPropietario;
	
	private String nome;
	private String rg;
	private String cpf;
	private String dataNascimento;
	private String dataFalecimento;
	private String sexo;
	private String estadoCivil;
	
	//Getters & Setters
	
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
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getDataFalecimento() {
		return dataFalecimento;
	}
	public void setDataFalecimento(String dataFalecimento) {
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
	public BeanClienteProprietario getCodCliPropietario() {
		return codCliPropietario;
	}
	public void setCodCliPropietario(BeanClienteProprietario codCliPropietario) {
		this.codCliPropietario = codCliPropietario;
	}
	
}
