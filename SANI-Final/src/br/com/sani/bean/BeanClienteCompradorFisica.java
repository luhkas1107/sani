package br.com.sani.bean;

public class BeanClienteCompradorFisica {
	
	private BeanClienteComprador codCliComprador;
	private BeanClienteComprador cep;
		
	private String nome;
	private String rg;
	private String cpf;
	private String dataNascimento;
	private String dataFalecimento;
	private String sexo;
	private String estadoCivil;
	private String renda;
	private String profissao;
	
	//Getters & Setters
	
	public BeanClienteComprador getCodCliComprador() {
		return codCliComprador;
	}
	public void setCodCliComprador(BeanClienteComprador codCliComprador) {
		this.codCliComprador = codCliComprador;
	}
	public BeanClienteComprador getCep() {
		return cep;
	}
	public void setCep(BeanClienteComprador cep) {
		this.cep = cep;
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
	public String getRenda() {
		return renda;
	}
	public void setRenda(String renda) {
		this.renda = renda;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
}
