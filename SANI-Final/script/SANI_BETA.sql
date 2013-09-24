-- Gerado por Oracle SQL Developer Data Modeler 3.3.0.747
--   em:        2013-08-28 17:01:10 BRT
--   site:      SQL Server 2008
--   tipo:      SQL Server 2008




CREATE
  TABLE tbClienteComprador
  (
    COD_CLI_COMPRADOR   DECIMAL (15) NOT NULL ,
    NM_CLI_COMPRADOR    VARCHAR (60) NOT NULL ,
    SX_CLI_COMPRADOR    CHAR (1) NOT NULL DEFAULT 'M' ,
    CPF_CLI_COMPRADOR   VARCHAR (11) NOT NULL ,
    RG_CLI_COMPRADOR    VARCHAR (9) NOT NULL ,
    RENDA_CLI_COMPRADOR DECIMAL (10,2) NOT NULL ,
    EC_CLI_COMPRADOR    CHAR (1) NOT NULL DEFAULT 'S' ,
    NAC_CLI_COMPRADOR   VARCHAR (30) NOT NULL ,
    END_CLI_COMPRADOR   VARCHAR (100) NOT NULL ,
    NUM_CLI_COMPRADOR   CHAR (10) NOT NULL ,
    COMP_CLI_COMPRADOR  VARCHAR (100) NOT NULL ,
    CEP_CLI_COMPRADOR   CHAR (8) NOT NULL ,
    EMAIL_CLI_COMPRADOR VARCHAR (120) NOT NULL ,
    SITE_CLI_COMPRADOR  VARCHAR (120) NOT NULL ,
    TEL_CLI_COMPRADOR   CHAR (8) NOT NULL ,
    CEL_CLI_COMPRADOR   CHAR (9) NOT NULL ,
    CONSTRAINT tbClienteComprador_PK PRIMARY KEY CLUSTERED (COD_CLI_COMPRADOR)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default" ,
  CONSTRAINT tbClienteComprador__UN UNIQUE NONCLUSTERED (EMAIL_CLI_COMPRADOR)
  ON "default"
  )
  ON "default"
GO
ALTER TABLE tbClienteComprador
ADD CONSTRAINT sexoCliCompChk
CHECK
(
  SX_CLI_COMPRADOR IN ('F', 'M')
)
GO
ALTER TABLE tbClienteComprador
ADD CONSTRAINT estadoCivilCliCompChk
CHECK
(
  EC_CLI_COMPRADOR IN ('S', 'C', 'D', 'V')
)
GO

CREATE
  TABLE tbClienteProprietario
  (
    COD_CLI_PROPRIETARIO   DECIMAL (15) NOT NULL ,
    NM_CLI_PROPRIETARIO    VARCHAR (60) NOT NULL ,
    SX_CLI_PROPRIETARIO    CHAR (1) NOT NULL DEFAULT 'M' ,
    CPF_CLI_PROPRIETARIO   VARCHAR (11) ,
    RD_CLI_PROPRIETARIO    DECIMAL (10,2) NOT NULL ,
    EC_CLI_PROPRIETARIO    CHAR (1) NOT NULL DEFAULT 'S' ,
    NAC_CLI_PROPRIETARIO   VARCHAR (30) NOT NULL ,
    END_CLI_PROPRIETARIO   VARCHAR (100) NOT NULL ,
    NUM_CLI_PROPRIETARIO   CHAR (10) NOT NULL ,
    COMP_CLI_PROPRIETARIO  VARCHAR (100) NOT NULL ,
    CEP_CLI_PROPRIETARIO   CHAR (8) NOT NULL ,
    EMAIL_CLI_PROPRIETARIO VARCHAR (120) NOT NULL ,
    SITE_CLI_PROPRIETARIO  VARCHAR (120) NOT NULL ,
    TEL_CLI_PROPRIETARIO   CHAR (8) NOT NULL ,
    CEL_CLI_PROPRIETARIO   CHAR (9) NOT NULL ,
    TP_CLI_PROPRIETARIO    CHAR (1) NOT NULL DEFAULT 'C' ,
    CONSTRAINT tbClienteProprietario_PK PRIMARY KEY CLUSTERED (
    COD_CLI_PROPRIETARIO)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default" ,
  CONSTRAINT tbClienteProprietario__UN UNIQUE NONCLUSTERED (
  EMAIL_CLI_PROPRIETARIO) ON "default"
  )
  ON "default"
GO
ALTER TABLE tbClienteProprietario
ADD CONSTRAINT sexoCliPropChk
CHECK
(
  SX_CLI_PROPRIETARIO IN ('F', 'M')
)
GO
ALTER TABLE tbClienteProprietario
ADD CONSTRAINT estadoCivilCliPropChk
CHECK
(
  EC_CLI_PROPRIETARIO IN ('S', 'C', 'D', 'V')
)
GO
ALTER TABLE tbClienteProprietario
ADD CONSTRAINT tipoPropriedadeCliPropChk
CHECK
(
  TP_CLI_PROPRIETARIO IN ('C', 'R')
)
GO

CREATE
  TABLE tbFuncionario
  (
    COD_FUNC   DECIMAL (15) NOT NULL ,
    NM__FUNC   VARCHAR (60) NOT NULL ,
    SX_FUNC    CHAR (1) NOT NULL DEFAULT 'M' ,
    CPF_FUNC   CHAR (11) NOT NULL ,
    RG_FUNC    CHAR (9) NOT NULL ,
    EC_FUNC    CHAR (1) NOT NULL DEFAULT 'S' ,
    NASC_FUNC  VARCHAR (30) NOT NULL ,
    END_FUNC   VARCHAR (100) NOT NULL ,
    NUM_FUNC   CHAR (10) NOT NULL ,
    COMP_FUNC  VARCHAR (100) NOT NULL ,
    CEP_FUNC   CHAR (8) NOT NULL ,
    TEL_FUNC   CHAR (8) NOT NULL ,
    CEL_FUNC   CHAR (9) ,
    CG_FUNC    CHAR (2) NOT NULL DEFAULT 'CI' ,
    LG_FUNC    VARCHAR (50) NOT NULL ,
    SN_FUNC    VARCHAR (50) NOT NULL ,
    CSN_FUNC   VARCHAR (50) ,
    EMAIL_FUNC VARCHAR (100) NOT NULL ,
    SITE_FUNC  VARCHAR (100) NOT NULL ,
    CONSTRAINT tbFuncionario_PK PRIMARY KEY CLUSTERED (COD_FUNC)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default" ,
  CONSTRAINT tbFuncionario__UN UNIQUE NONCLUSTERED (LG_FUNC) ON "default" ,
  CONSTRAINT tbFuncionario__UNv2 UNIQUE NONCLUSTERED (EMAIL_FUNC) ON "default"
  )
  ON "default"
GO
ALTER TABLE tbFuncionario
ADD CONSTRAINT sexo_funcionarioChk
CHECK
(
  SX_FUNC IN ('F', 'M')
)
GO
ALTER TABLE tbFuncionario
ADD CONSTRAINT estadoCivil_funcionarioChk
CHECK
(
  EC_FUNC IN ('S', 'C', 'D', 'V')
)
GO
ALTER TABLE tbFuncionario
ADD CONSTRAINT cargo_funcionarioChk
CHECK
(
  CG_FUNC IN ('CI', 'S', 'G', 'C')
)
GO

CREATE
  TABLE tbMetas
  (
    COD_META  DECIMAL (15) NOT NULL ,
    DESC_META VARCHAR (500) NOT NULL ,
    DI_META   DATE NOT NULL ,
    DF_META   DATE NOT NULL ,
    ST_META   CHAR (1) NOT NULL DEFAULT 'A' ,
    CONSTRAINT tbMetas_PK PRIMARY KEY CLUSTERED (COD_META)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO
ALTER TABLE tbMetas
ADD CONSTRAINT check_st
CHECK
(
  ST_META IN ('A', 'F')
)
GO

CREATE
  TABLE tbPropriedades
  (
    COD_PROP             DECIMAL (15) NOT NULL ,
    COD_CLI_PROPRIETARIO DECIMAL (15) NOT NULL ,
    TPC_PROP             CHAR (1) NOT NULL DEFAULT 'T' ,
    TPR__PROP            CHAR (1) NOT NULL DEFAULT 'T' ,
    EI_PROP              CHAR (1) NOT NULL DEFAULT 'L' ,
    NM_PROP              VARCHAR (60) NOT NULL ,
    CPF_PROP             CHAR (11) NOT NULL ,
    EMAIL_PROP           VARCHAR (120) NOT NULL ,
    END_PROP             VARCHAR (100) NOT NULL ,
    CEP_PROP             CHAR (8) NOT NULL ,
    NUM_PROP             CHAR (10) NOT NULL ,
    COMP_PROP            VARCHAR (100) NOT NULL ,
    MTG_PROP             VARCHAR (100) NOT NULL ,
    CONSTRAINT tbPropriedades_PK PRIMARY KEY CLUSTERED (COD_PROP)
WITH
  (
    ALLOW_PAGE_LOCKS = ON ,
    ALLOW_ROW_LOCKS  = ON
  )
  ON "default"
  )
  ON "default"
GO
ALTER TABLE tbPropriedades
ADD CONSTRAINT TP_PROPComercialPropChk
CHECK
(
  TPC_PROP IN ('T', 'G', 'S', 'P', 'I')
)
GO
ALTER TABLE tbPropriedades
ADD CONSTRAINT TP_PROPRsidencialPropChk
CHECK
(
  TPR__PROP IN ('T', 'C', 'A', 'S')
)
GO
ALTER TABLE tbPropriedades
ADD CONSTRAINT estado_imovelPropChk
CHECK
(
  EI_PROP IN ('L', 'V', 'D', 'A')
)
GO

ALTER TABLE tbPropriedades
ADD CONSTRAINT fkClientePropriedade FOREIGN KEY
(
COD_CLI_PROPRIETARIO
)
REFERENCES tbClienteProprietario
(
COD_CLI_PROPRIETARIO
)
ON
DELETE
  NO ACTION ON
UPDATE NO ACTION
GO


-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             5
-- CREATE INDEX                             0
-- ALTER TABLE                             13
-- CREATE VIEW                              0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE DATABASE                          0
-- CREATE DEFAULT                           0
-- CREATE INDEX ON VIEW                     0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE ROLE                              0
-- CREATE RULE                              0
-- CREATE PARTITION FUNCTION                0
-- CREATE PARTITION SCHEME                  0
-- 
-- DROP DATABASE                            0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
