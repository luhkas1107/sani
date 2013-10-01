--===========================================================================
--INSERT CLIENTE COMPRADOR PESSOA FISICA
--===========================================================================

INSERT INTO TB_CLI_COMP_FISICA (
		COD_CLI_COMPRADOR,
		NOME_PESSOA,
		RG_PESSOA,
		CPF_PESSOA,
		DT_NASCIMENTO,
		DT_FALESCIMENTO,
		SEXO_PESSOA,
		EST_CIVIL_PESSOA)
VALUES	(?,?,?,?,?,?,?,?);


--===========================================================================
--UPDATE CLIENTE COMPRADOR PESSOA FISICA
--===========================================================================

UPDATE TB_CLI_COMP_FISICA SET
		NOME_PESSOA = ?,
		RG_PESSOA = ?,
		CPF_PESSOA = ?,
		DT_NASCIMENTO = ?,
COD_CLI_COMPRADOR		DT_FALESCIMENTO = ?,
		SEXO_PESSOA = ?,
		EST_CIVIL_PESSOA = ?
WHERE	COD_CLI_COMPRADOR = ?;

--===========================================================================
--DELETE CLIENTE COMPRADOR PESSOA FISICA
--===========================================================================

DELETE FROM TB_CLI_COMP_FISICA WHERE COD_CLI_COMPRADOR = ?;

--===========================================================================
--INSERT CLIENTE COMPRADOR PESSOA JURIDICA
--===========================================================================

INSERT INTO TB_CLI_COMP_JURIDICA (
		COD_CLI_COMPRADOR,
		RAZAO_SOCIAL,
		CNPJ_PESSOA,
		INSC_ESTADUAL_PESSOA,
		DT_FUNDACAO,
		RAMO_ATIVIDADE)
VALUES	(?,?,?,?,?,?);

--===========================================================================
--UPDATE CLIENTE COMPRADOR PESSOA JURIDICA
--===========================================================================

UPDATE TB_CLI_COMP_JURIDICA SET
		RAZAO_SOCIAL = ?,
		CNPJ_PESSOA = ?,
		INSC_ESTADUAL_PESSOA = ?,
		DT_FUNDACAO = ?,
		RAMO_ATIVIDADE = ?
WHERE	COD_CLI_COMPRADOR = ?;

--===========================================================================
--DELETE CLIENTE COMPRADOR PESSOA JURIDICA
--===========================================================================		

DELETE TB_CLI_COMP_JURIDICA WHERE COD_CLI_COMPRADOR = ?;

--===========================================================================
--INSERT CLIENTE PROPRIETARIO PESSOA FISICA
--===========================================================================

INSERT INTO TB_CLI_PROP_FISICA (
		COD_CLI_PROPRIETARIO,
		NOME_PESSOA,
		RG_PESSOA,
		CPF_PESSOA,
		DT_NASCIMENTO,
		DT_FALESCIMENTO,
		SEXO_PESSOA,
		EST_CIVIL_PESSOA)
VALUES	(?,?,?,?,?,?,?,?);

--===========================================================================
--UPDATE CLIENTE PROPRIETARIO PESSOA FISICA
--===========================================================================

UPDATE TB_CLI_PROP_FISICA SET
		NOME_PESSOA = ?,
		RG_PESSOA= ?,
		CPF_PESSOA = ?,
		DT_NASCIMENTO = ?,
		DT_FALESCIMENTO = ?,
		SEXO_PESSOA = ?,
		EST_CIVIL_PESSOA = ?
WHERE	COD_CLI_PROPRIETARIO = ?;

--===========================================================================
--DELETE CLIENTE PROPRIETARIO PESSOA JURIDICA
--===========================================================================

DELETE FROM TB_CLI_PROP_FISICA WHERE COD_CLI_PROPRIETARIO = ?;

--===========================================================================
--INSERT CLIENTE PROPRIETARIO PESSOA JURIDICA
--===========================================================================

INSERT INTO TB_CLI_PROP_JURIDICA (
		COD_CLI_PROPRIETARIO,
		RAZAO_SOCIAL,
		CNPJ_PESSOA,
		INSC_ESTADUAL_PESSOA,
		DT_FUNDACAO,
		RAMO_ATIVIDADE)
VALUES	(?,?,?,?,?,?);

--===========================================================================
--UPDATE CLIENTE PROPRIETARIO PESSOA JURIDICA
--===========================================================================

UPDATE TB_CLI_PROP_JURIDICA SET
		RAZAO_SOCIAL = ?,
		CNPJ_PESSOA = ?,
		INSC_ESTADUAL_PESSOA = ?,
		DT_FUNDACAO = ?,
		RAMO_ATIVIDADE = ?
WHERE	COD_CLI_PROPRIETARIO = ?;

--===========================================================================
--DELETE CLIENTE PROPRIETARIO PESSOA JURIDICA
--===========================================================================

DELETE FROM TB_CLI_PROP_JURIDICA WHERE COD_CLI_PROPRIETARIO = ?;


--===========================================================================
--INSERT CLIENTE COMPRADOR
--===========================================================================

INSERT INTO TB_CLIENTE_COMPRADOR (
		COD_CLI_COMPRADOR,
		CEP,
		NR_ENDERECO,
		COMPL_ENDERECO,
		RENDA,
		PROFISSAO)
VALUES	(?,?,?,?,?,?);

--===========================================================================
--UPDATE CLIENTE COMPRADOR
--===========================================================================

UPDATE TB_CLIENTE_COMPRADOR SET
		CEP = ?,
		NR_ENDERECO = ?,
		COMPL_ENDERECO = ?,
		RENDA = ?,
		PROFISSAO = ?
WHERE	COD_CLI_COMPRADOR = ?;

--===========================================================================
--DELETE CLIENTE COMPRADOR
--===========================================================================

DELETE FROM TB_CLIENTE_COMPRADOR WHERE COD_CLI_COMPRADOR = ?;

--===========================================================================
--SELECT POR ID CLIENTE COMPRADOR
--===========================================================================

