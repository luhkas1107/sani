--Cria o DB SANI
create database SANI
go

--Usa o DB SANI
use SANI
go

--Tabela Cliente Comprador
create table tbClienteComprador(
id_ClienteComprador bigint not null constraint id_ClienteCompradorPk primary key (id_ClienteComprador),
nome_ClienteComprador varchar(50) not null,
sexo_ClienteComprador char(1) not null constraint sexoCliCompChk check (sexo in ('F','M')),
cpf_ClienteComprador varchar(11) not null constraint cpfCliCompPk primary key (cpf),
rg_ClienteComprador varchar(9),
renda_ClienteComprador numeric(10,2) not null,
estadoCivil_ClienteComprador char(1) not null constraint estadoCivilCliCompChk 
check (estadoCivil in ('S', 'C', 'D', 'V')),																		
nacionalidade_ClienteComprador char(50),
endereco_ClienteComprador varchar (100) not null,
numeroEndereco_ClienteComprador varchar(6) not null,
complementoEndereco_ClienteComprador varchar(20),
cep_ClienteComprador char(8) not null,
email_ClienteComprador varchar(50) constraint emailClienteUnico not null unique,
site_ClienteComprador varchar(50),
telefoneResidencial_ClienteComprador char(10),
telefoneCelular_ClienteComprador varchar(11),
)
go

--Tabela Cliente Proprietario
create table tbClienteProprietario(
id_ClienteProprietario bigint not null constraint id_ClienteProprietarioPk primary key (id_ClienteProprietario),
nome_ClienteProprietario varchar(50) not null,
sexo_ClienteProprietario char(1) not null constraint sexoCliPropChk check (sexo in ('F','M')),
cpf_ClienteProprietario varchar(11) not null constraint cpfCliPropPk primary key (cpf),
rg_ClienteProprietario varchar(9),
renda_ClienteProprietario numeric(10,2) not null,
estadoCivil_ClienteProprietario char(14) not null constraint estadoCivilCliPropChk check (estadoCivil in ('Solteiro (a)',
																			  'Casado (a)',
																			  'Divorciado (a)',
																			  'Viúvo (a)')),																		
nacionalidade_ClienteProprietario char(50),
endereco_ClienteProprietario varchar (100) not null,
numeroEndereco_ClienteProprietario varchar(6) not null,
complementoEndereco_ClienteProprietario varchar(20),
cep_ClienteProprietario char(8) not null,
email_ClienteProprietario varchar(50) constraint emailClienteUnico not null unique,
site_ClienteProprietario varchar(50),
telefoneResidencial_ClienteProprietario char(10),
telefoneCelular_ClienteProprietario varchar(11),
tipoPropriedade_ClienteProprietario varchar(20) not null constraint tipoPropriedadeCliPropChk check (tipoPropriedade in ('Comercial',
																							  'Residencial'))
)
go

--Tabela tbPropriedades
create table tbPropriedades(
idPropriedade bigint not null constraint id_ClienteProprietarioPk primary key (id_ClienteProprietario),
tipoPropriedadeComercial varchar(20) not null constraint tipoPropriedadeComercialPropChk check (tipoPropriedadeComercial in ('Terreno',
																													     'Galpão',
																													     'Sala',
																													     'Prédio',
																														 'imovel p/ renda')),
																														 
tipoPropriedadeResidencial varchar (20) not null constraint tipoPropriedadeRsidencialPropChk check (tipoPropriedadeResidencial in ('Terreno',
                                                                                                                               'Casa',
                                                                                                                               'Apartamento',
                                                                                                                               'Sobrado')),
estado_imovel varchar (20) not null constraint estado_imovelPropChk check (estado_imovel in ('À Venda',
                                                                                             'Vendida',
                                                                                             'Aluguel',
                                                                                             'Alugada')),
                                                                                            
nome_proprietario varchar (50) not null, 
cpf_proprietario char (11) not null constraint cpf_proprietarioFuncPk primary key (cpf_proprietario),
email_proprietario varchar (50) not null,
endereco_propriedade varchar (100) not null,
cep_propriedade char (8) not null,
numero_propriedade varchar(6) not null,
complemento_propriedade varchar(20),
metragemPropriedade varchar(20)                                                                                                 
)
go

--Tabela tbFuncionario
create table tbFuncionario(
id_funcionario bigint not null constraint id_funcionarioPk primary key (id_funcionario),
--n_creci char (50) not null,-- no caso somente do funcionario com cargo de corretor -- 
nome_funcionario varchar (50) not null,
sexo_funcionario char(1) not null constraint sexo_funcionarioChk check (sexo in ('F','M')),
cpf_funcionario char (11) not null,
rg_funcionario varchar (8) not null,
estadoCivil_funcionario char(14) not null constraint estadoCivil_funcionarioChk check (estadoCivil in ('Solteiro (a)',
																			  'Casado (a)',
																			  'Divorciado (a)',
																			  'Viúvo (a)')),							
nacionalidade_funcionario char (50) not null,
endereco_funcionario varchar (100) not null,
numeroEndereco_funcionario varchar(6) not null,
complementoEndereco_funcionario varchar(20),
cep_funcionario char(8) not null,
telefoneResidencial_funcionario char(10),
telefoneCelular_funcionario varchar(11),
cargo_funcionario char(14) not null constraint cargo_funcionarioChk check (cargo in ('Corretor(a)de Imoveis',
															 'Secretario(a)',
															 'Gerente',
														     'Consultor(a)')),
														     
login_funcionario varchar(50) constraint login_funcionarioUnico not null unique,
senha_funcionario varchar (50) not null,
confsenha_funcionario varchar (50) not null,
email_funcionario varchar(50) constraint email_funcionarioUnico not null unique,
site_funcionario varchar(50)
)
go

--Tabela tbMetas
create table tbMetas(
idMeta int not null,
descrMeta varchar (500) not null,
dataInicio Date not null,
dataFinal Date not null,
stMeta char(1) not null default = 'A' constraint check_st (stMeta in ('A', 'F')) 
)
go

select * from tbClienteComprador
select * from tbClienteProprietario
select * from tbFuncionario
select * from tbMetas
select * from tbPropriedades