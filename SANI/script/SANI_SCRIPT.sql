CREATE DATABASE SANI
GO

USE SANI
GO

CREATE TABLE tbClienteComprador (
  codCliComprador     NUMERIC (11) NOT NULL ,
  cep                 VARCHAR (8) NOT NULL ,
  numeroEndereco      NUMERIC (11) NOT NULL ,
  complementoEndereco VARCHAR (1024) NOT NULL ,
  telCliComprador     VARCHAR (10) NOT NULL ,
  celCliComprador     VARCHAR (11) ,
  tpCliente			  VARCHAR (2) NOT NULL DEFAULT 'PF',

  CONSTRAINT PK_tbClienteComprador PRIMARY KEY (codCliComprador)
 )
GO

CREATE TABLE tbClienteProprietario (
  codCliProprietario  NUMERIC (11) NOT NULL ,
  cep                 VARCHAR (8) NOT NULL ,
  numeroEndereco      NUMERIC (11) NOT NULL ,
  complementoEndereco VARCHAR (120) ,
  telCliProprietario  VARCHAR (10) NOT NULL ,
  celCliProprietario  VARCHAR (11) ,
  tpCliente			  VARCHAR (2) NOT NULL DEFAULT 'PF',

  CONSTRAINT PK_tbClienteProprietario PRIMARY KEY (codCliProprietario)
)
GO

CREATE TABLE tbClienteCompradorFisica (
  codCliComprador   NUMERIC (11) NOT NULL ,
  nomePessoa        VARCHAR (120) NOT NULL ,
  rgPessoa          VARCHAR (16) NOT NULL ,
  cpfPessoa         VARCHAR (11) NOT NULL ,
  dtNasc            DATE NOT NULL ,
  dtFalescimento    DATE ,
  sexoPessoa        CHAR NOT NULL DEFAULT 'M' ,
  estadoCivilPessoa CHAR NOT NULL ,
  renda             NUMERIC (11,2) NOT NULL ,
  profissao         VARCHAR (255) NOT NULL ,
  email             VARCHAR (120) NOT NULL ,

  CONSTRAINT PK_tbClienteCompradorFisica PRIMARY KEY (codCliComprador)
)
GO

CREATE TABLE tbClienteCompradorJuridica (
  codCliComprador     NUMERIC (11) NOT NULL ,
  razaoSocial         VARCHAR (1024) NOT NULL ,
  email               VARCHAR (120) NOT NULL,
  cnpj                VARCHAR (14) NOT NULL ,
  inscricaoEstadual   VARCHAR (30) NOT NULL ,
  dtFundacao          DATE NOT NULL ,
  ramoAtividade       VARCHAR (1024) NOT NULL ,

  CONSTRAINT PK_tbClienteCompradorJuridica PRIMARY KEY (codCliComprador)
)
GO

CREATE TABLE tbClienteProprietarioFisica (
  codCliProprietario  NUMERIC (11) NOT NULL ,
  nomePessoa          VARCHAR (120) NOT NULL ,
  rgPessoa            VARCHAR (16) NOT NULL ,
  cpfPessoa           VARCHAR (11) NOT NULL ,
  dtNasc              DATE NOT NULL ,
  dtFalescimento      DATE ,
  sexoPessoa          CHAR NOT NULL DEFAULT 'M' ,
  estadoCivilPessoa   CHAR NOT NULL ,
  email               VARCHAR (120) NOT NULL ,
  
  CONSTRAINT PK_tbClienteProprietarioFisica PRIMARY KEY (codCliProprietario)
)
GO

CREATE TABLE tbClienteProprietarioJuridica (
  codCliProprietario  NUMERIC (11) NOT NULL ,
  razaoSocial         VARCHAR (255) NOT NULL ,
  cnpj                VARCHAR (14) NOT NULL ,
  inscricaoEstadual   VARCHAR (30) NOT NULL ,
  dtFundacao          DATE NOT NULL ,
  ramoAtividade       VARCHAR (1024) NOT NULL ,
  CONSTRAINT PK_tbClienteProprietarioJuridica PRIMARY KEY (codCliProprietario)
)
GO

CREATE TABLE tbCorretor (
  codCorretor           NUMERIC (11) NOT NULL ,
  cep                   VARCHAR (8) NOT NULL ,
  numeroEndereco        NUMERIC (11) NOT NULL ,
  complementoEndereco   VARCHAR (255) NOT NULL ,
  nomeCorretor          VARCHAR (120) NOT NULL ,
  cpfCorretor           VARCHAR (11) NOT NULL ,
  rgCorretor            VARCHAR (16) NOT NULL ,
  numeroCreciCorretor   VARCHAR (50) NOT NULL ,
  sexoCorretor          CHAR NOT NULL DEFAULT 'M' ,
  dtNasc                DATE NOT NULL ,
  estadoCivilCorretor   CHAR NOT NULL ,

  CONSTRAINT PK_tbCorretor PRIMARY KEY (codCorretor)
)
GO


CREATE TABLE tbImagensPropriedade (
  idImagem        NUMERIC (11) IDENTITY NOT NULL ,
  codPropriedade  NUMERIC (11) NOT NULL ,
  dtImagem        DATE NOT NULL ,
  imagem		  VARBINARY(MAX) NOT NULL,

  CONSTRAINT PK_tbImagensPropriedade PRIMARY KEY (idImagem, codPropriedade)
)
GO

CREATE TABLE tbPropriedade (
  codPropriedade       NUMERIC (11) NOT NULL ,
  codCliProprietario   NUMERIC (11) NOT NULL ,
  cep                  VARCHAR (8) NOT NULL ,
  numeroEndereco       NUMERIC (11) NOT NULL ,
  complementoEndereco  VARCHAR (255) ,
  tipoPropriedade      VARCHAR (15) NOT NULL,
  situacaoPropriedade  VARCHAR (15) NOT NULL,
  metragemPropriedade  NUMERIC (11,2) NOT NULL ,
  valorPropriedade	   NUMERIC (11, 2) NOT NULL,

  CONSTRAINT PK_tbPropriedade PRIMARY KEY (codPropriedade)
)
GO


CREATE TABLE tbMetas (
  codMeta      NUMERIC (11) NOT NULL ,
  codCorretor  NUMERIC (11) NOT NULL ,
  dtInicio     DATE NOT NULL ,
  dtFinal      DATE NOT NULL ,
  descrMeta    VARCHAR (255) NOT NULL ,

  CONSTRAINT PK_tbMetas PRIMARY KEY (codMeta)
)
GO

CREATE TABLE tbProposta (
  codProposta       NUMERIC (11) IDENTITY NOT NULL ,
  codCliComprador   NUMERIC (11) NOT NULL ,
  codPropriedade    NUMERIC (11) NOT NULL ,
  ValorProposta     NUMERIC (11,2) NOT NULL ,
  validadeProposta  DATE NOT NULL ,
  estadoProposta    VARCHAR (15) NOT NULL DEFAULT 'Negada' ,
  statusProposta    VARCHAR (1) NOT NULL DEFAULT 'A' ,
  formaPagamento	VARCHAR (150) NOT NULL,
  dataProposta		DATE NOT NULL DEFAULT GETDATE(),
  tipoProposta		VARCHAR (1)	NOT NULL DEFAULT 'V',

  CONSTRAINT PK_tbProposta PRIMARY KEY (codProposta)
)
GO

CREATE TABLE tbUsuario (
  usuario       VARCHAR (50) NOT NULL ,
  senha         VARCHAR (50) NOT NULL ,
  nome          VARCHAR (120) NOT NULL ,
  email         VARCHAR (255) NOT NULL ,
  permissao      VARCHAR (1) NOT NULL ,

  CONSTRAINT PK_tbUsuario PRIMARY KEY (usuario)
)
GO

CREATE TABLE tbVendaLocacao (
  codTransacao      NUMERIC (11) IDENTITY NOT NULL ,
  dtTransacao       DATE NOT NULL ,
  numeroContrato    NUMERIC (11) NOT NULL ,
  arqContrato       VARBINARY NOT NULL ,
  codProposta       NUMERIC (11) NOT NULL ,

  CONSTRAINT PK_tbVendaLocacao PRIMARY KEY (codTransacao)
)
GO

CREATE TABLE tbCidade (
  idCidade  INTEGER NOT NULL ,
  cidade    VARCHAR (255) NOT NULL ,
  uf        VARCHAR (2) NOT NULL ,

  CONSTRAINT PK_tbCidade PRIMARY KEY (idCidade)
)
GO

CREATE TABLE tbBairro (
  idBairro INTEGER NOT NULL ,
  bairro   VARCHAR (255) NOT NULL ,
  idCidade INTEGER NOT NULL ,

  CONSTRAINT PK_tbBairro PRIMARY KEY (idBairro)
)
GO

CREATE TABLE tbEndereco (
  cep       VARCHAR (8) NOT NULL DEFAULT '' ,
  endereco  VARCHAR (255) NOT NULL DEFAULT '' ,
  idCidade  INTEGER NOT NULL ,
  idBairro  INTEGER NOT NULL ,

  CONSTRAINT PK_tbEndereco PRIMARY KEY (cep)
)
GO

ALTER TABLE tbEndereco ADD CONSTRAINT FK_Bairro_Endereco FOREIGN KEY(idBairro) REFERENCES tbBairro(idBairro)
GO

ALTER TABLE tbBairro ADD CONSTRAINT FK_Cidade_Bairro FOREIGN KEY(idCidade) REFERENCES tbCidade(idCidade)
GO

ALTER TABLE tbEndereco ADD CONSTRAINT FK_Cidade_Endereco FOREIGN KEY(idCidade) REFERENCES tbCidade(idCidade)
GO

ALTER TABLE tbClienteCompradorFisica ADD CONSTRAINT FK_ClienteCompradorFisica FOREIGN KEY(codCliComprador) REFERENCES tbClienteComprador(codCliComprador)
GO

ALTER TABLE tbClienteCompradorJuridica ADD CONSTRAINT FK_ClienteCompradorJuridica FOREIGN KEY(codCliComprador) REFERENCES tbClienteComprador(codCliComprador)
GO

ALTER TABLE tbProposta ADD CONSTRAINT FK_ClienteComprador_Proposta FOREIGN KEY(codCliComprador) REFERENCES tbClienteComprador(codCliComprador)
GO

ALTER TABLE tbPropriedade ADD CONSTRAINT FK_ClienteProprietario_IMOVEL FOREIGN KEY(codCliProprietario) REFERENCES tbClienteProprietario(codCliProprietario)
GO

ALTER TABLE tbClienteProprietarioFisica ADD CONSTRAINT FK_ClienteProprietarioFisica FOREIGN KEY(codCliProprietario) REFERENCES tbClienteProprietario(codCliProprietario)
GO

ALTER TABLE tbClienteProprietarioJuridica ADD CONSTRAINT FK_ClienteProprietarioJuridica FOREIGN KEY(codCliProprietario) REFERENCES tbClienteProprietario(codCliProprietario)
GO

ALTER TABLE tbMetas ADD CONSTRAINT FK_Corretor_Meta FOREIGN KEY(codCorretor) REFERENCES tbCorretor(codCorretor)
GO

ALTER TABLE tbClienteComprador ADD CONSTRAINT FK_Endereco_ClienteComprador FOREIGN KEY(cep) REFERENCES tbEndereco(cep)
GO

ALTER TABLE tbClienteProprietario ADD CONSTRAINT FK_Endereco_ClienteProprietario FOREIGN KEY(cep) REFERENCES tbEndereco(cep)
GO

ALTER TABLE tbCorretor ADD CONSTRAINT FK_Endereco_Corretor FOREIGN KEY(cep) REFERENCES tbEndereco(cep)
GO

ALTER TABLE tbPropriedade ADD CONSTRAINT FK_Endereco_Propriedade FOREIGN KEY(cep) REFERENCES tbEndereco(cep)
GO

ALTER TABLE tbImagensPropriedade ADD CONSTRAINT FK_Propriedade_Imagens FOREIGN KEY(codPropriedade) REFERENCES tbPropriedade(codPropriedade)
GO

ALTER TABLE tbProposta ADD CONSTRAINT FK_Propriedade_Proposta FOREIGN KEY(codPropriedade) REFERENCES tbPropriedade(codPropriedade)
GO

ALTER TABLE tbVendaLocacao ADD CONSTRAINT FK_Proposta_VendaLoc FOREIGN KEY(codProposta) REFERENCES tbProposta(codProposta)
GO
