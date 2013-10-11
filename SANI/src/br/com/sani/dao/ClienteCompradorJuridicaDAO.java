package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sani.bean.ClienteComprador;
import br.com.sani.bean.ClienteCompradorFisica;
import br.com.sani.bean.ClienteComprador;
import br.com.sani.bean.Metas;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class ClienteCompradorJuridicaDAO {

	private static final String EXCLUIR_CLIENTE_COMPRADOR_FISICA = 
			"delete from tbClienteComprador where id_ClienteComprador = ?";
	
	private static final String INSERIR_CLIENTE_COMPRADOR_FISICA =
			"insert into tbClienteComprador" +
			"(nome_ClienteComprador," +
			"sexo_ClienteComprador," +
			"cpf_ClienteComprador," +
			"rg_ClienteComprador," +
			"renda_ClienteComprador," +
			"estadoCivil_ClienteComprador," +
			//"nacionalidade_ClienteComprador," +
			"endereco_ClienteComprador," +
			"numeroEndereco_ClienteComprador," +
			"complementoEndereco_ClienteComprador," +
			"cep_ClienteComprador," +
			"telefoneResidencial_ClienteComprador," +
			"telefoneCelular_ClienteComprador," +
			"email_ClienteComprador," +
			//"site_ClienteComprador," +
			"id_ClienteComprador)" +
			"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String ATUALIZAR_CLIENTE_COMPRADOR_FISICA =
			"update tbClienteComprador set " +
			"nome_ClienteComprador = ? " +
			"sexo_ClienteComprador = ? " +
			"cpf_ClienteComprador = ? " +
			"rg_ClienteComprador = ? " +
			"renda_ClienteComprador = ?" +
			"estadoCivil_ClienteComprador = ? " +
			//"nacionalidade_ClienteComprador = ? " +
			"endereco_ClienteComprador = ? " +
			"numeroEndereco_ClienteComprador = ? " +
			"complementoEndereco_ClienteComprador = ? " +
			"cep_ClienteComprador = ? " +
			"telefoneResidencial_ClienteComprador = ? " +
			"telefoneCelular_ClienteComprador = ? " +
			"email_ClienteComprador = ? " +
			//"site_ClienteComprador = ? " +			
			"where id_ClienteComprador = ? ";
	
	/*private static final String VALIDAR_LOGIN_SENHA = 
		"select "+
			"count(id_ClienteComprador) as total " +
		"from "+
			"tbClienteComprador cc "+
		"where "+
			"cc.login_funcionario = ? and " +
			"cc.senha_funcionario = ?";*/
	
	private static final  String CONSULTA_CLIENTE_COMPRADOR_FISICA =
			"select * from tbClienteComprador order by nome_ClienteComprador";

	private static final  String CONSULTA_CLIENTE_COMPRADOR_FISICA_NOME =
			"select * from tbClienteComprador where nome_ClienteComprador like ? order by nome_ClienteComprador";
	
	private static final  String CONSULTA_CLIENTE_COMPRADOR_FISICA_ID = 
			"select * from tbClienteComprador where id_ClienteComprador = ?";
	
	private static final String BUSCAR_NOVO_ID = 
			"SELECT ISNULL(MAX(COD_CLI_COMPRADOR), 0) + 1 AS NOVO_ID FROM TB_CLIENTE_COMPRADOR";
	
	private ClienteComprador getBean(ResultSet result) throws SQLException, DAOException{
		ClienteComprador cliComp = new ClienteComprador();
		
		cliComp.setCodCliComprador(result.getInt(""));
		cliComp.setCep(result.getString(""));
		cliComp.setNumeroEndereco(result.getString(""));
		cliComp.setComplementoEndereco(result.getString(""));
			
		return cliComp;
	}
	
	/**
	 * Este método busca no banco de dados qual vai ser o próximo numero do código
	 * @return um numero inteiro
	 * @throws DAOException
	 */
	private int getNovoId() throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		int retorno = 0;
		try{
			statement = conn.prepareStatement(BUSCAR_NOVO_ID);
			result = statement.executeQuery();
			
			if(result.next()){
				retorno = result.getInt("NOVO_ID");
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
		
		return retorno;
	}
	
	
	//INSERIR
	public void inserirClienteCompradorFisica(ClienteCompradorFisica cliComp) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(INSERIR_CLIENTE_COMPRADOR_FISICA);
			statement.setInt(1, getNovoId());
			statement.setString(2, cliComp.getCpf());
			statement.setString(3, cliComp.getRg());
			statement.setString(4, cliComp.getProfissao());
			
			
			statement.executeUpdate();
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
	}
	
	//ATUALIZAR
	
	//CONSULTA NOME
	
	public ClienteComprador consultaPorCod(int codCliComprador) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		ClienteComprador retorno = null;
		try{
			statement = conn.prepareStatement(CONSULTA_CLIENTE_COMPRADOR_FISICA_ID);
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
	
	public ClienteComprador excluirClienteComprador(int codCliComprador) throws DAOException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		ClienteComprador retorno = null;
		try{
			statement = conn.prepareStatement(CONSULTA_CLIENTE_COMPRADOR_FISICA_ID);
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