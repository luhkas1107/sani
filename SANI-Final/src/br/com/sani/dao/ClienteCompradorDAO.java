package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sani.bean.BeanClienteComprador;
import br.com.sani.bean.ClienteComprador;
import br.com.sani.bean.Metas;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class ClienteCompradorDAO {

	private static final String EXCLUIR_CLIENTE_COMPRADOR = 
			"delete from TB_CLI_COMPRADOR where nome_ClienteComprador = ?";
	
	private static final String INSERIR_CLIENTE_COMPRADOR =
			"insert into TB_CLI_COMPRADOR" +
			"(nome_ClienteComprador," +
			"sexo_ClienteComprador," +
			"cpf_ClienteComprador," +
			"rg_ClienteComprador," +
			"renda_ClienteComprador," +
			"estadoCivil_ClienteComprador," +
			"nacionalidade_ClienteComprador," +
			"endereco_ClienteComprador," +
			"numeroEndereco_ClienteComprador," +
			"complementoEndereco_ClienteComprador," +
			"cep_ClienteComprador," +
			"telefoneResidencial_ClienteComprador," +
			"telefoneCelular_ClienteComprador," +
			"email_ClienteComprador," +
			"site_ClienteComprador," +
			"id_ClienteComprador)" +
			"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String ATUALIZAR_CLIENTE_COMPRADOR =
			"update TB_CLI_COMPRADOR set " +
			"nome_ClienteComprador = ? " +
			"sexo_ClienteComprador = ? " +
			"cpf_ClienteComprador = ? " +
			"rg_ClienteComprador = ? " +
			"renda_ClienteComprador = ?" +
			"estadoCivil_ClienteComprador = ? " +
			"nacionalidade_ClienteComprador = ? " +
			"endereco_ClienteComprador = ? " +
			"numeroEndereco_ClienteComprador = ? " +
			"complementoEndereco_ClienteComprador = ? " +
			"cep_ClienteComprador = ? " +
			"telefoneResidencial_ClienteComprador = ? " +
			"telefoneCelular_ClienteComprador = ? " +
			"email_ClienteComprador = ? " +
			"site_ClienteComprador = ? " +			
			"where id_ClienteComprador = ? ";
	
	/*private static final String VALIDAR_LOGIN_SENHA = 
		"select "+
			"count(id_ClienteComprador) as total " +
		"from "+
			"tbClienteComprador cc "+
		"where "+
			"cc.login_funcionario = ? and " +
			"cc.senha_funcionario = ?";*/
	
	private static final  String CONSULTA_CLIENTE_COMPRADOR =
			"select * from TB_CLI_COMPRADOR order by nome_ClienteComprador";

	private static final  String CONSULTA_CLIENTE_COMPRADOR_NOME =
			"select * from TB_CLI_COMPRADOR where nome_ClienteComprador like ? order by nome_ClienteComprador";
	
	private static final  String CONSULTA_CLIENTE_COMPRADOR_ID = 
			"select * from TB_CLI_COMPRADOR where COD_CLI_COMPRADOR = ?";
	
	private BeanClienteComprador getBean(ResultSet result) throws SQLException, DAOException{
		BeanClienteComprador cliComp = new BeanClienteComprador();
		
		cliComp.setCodCliComprador(result.getInt(""));
		cliComp.setCep(result.getString(""));
		cliComp.setNumeroEndereco(result.getString(""));
		cliComp.setComplementoEndereco(result.getString(""));
		cliComp.setRenda(result.getString(""));
		cliComp.setProfissao(result.getString(""));
		
		return cliComp;
	}
	
	//INSERIR
	
	//ATUALIZAR
	
	//CONSULTA NOME
	
	public BeanClienteComprador consultaPorCod(int codCliComprador) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		BeanClienteComprador retorno = null;
		try{
			statement = conn.prepareStatement(CONSULTA_CLIENTE_COMPRADOR_ID);
			statement.setInt(1, codCliComprador);
			result = statement.executeQuery();
			if(result.next()){
				retorno = getBean(result);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
		
		return retorno;
	}
	
	public BeanClienteComprador excluirClienteComprador(int codCliComprador) throws DAOException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		BeanClienteComprador retorno = null;
		try{
			statement = conn.prepareStatement(CONSULTA_CLIENTE_COMPRADOR_ID);
			statement.setInt(1, codCliComprador);
			result = statement.executeQuery();
			if(result.next()){
				retorno = getBean(result);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
		
		return retorno;
	}
	
	
}