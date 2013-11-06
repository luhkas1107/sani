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
import br.com.sani.bean.ClienteCompradorJuridica;
import br.com.sani.bean.Metas;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class ClienteCompradorJuridicaDAO {

	private static final String EXCLUIR_CLIENTE_COMPRADOR_JURIDICA = 
			"delete from tbClienteCompradorJuridica where codCliComprador = ?; "+
			"delete from tbClienteComprador where codCliComprador = ?; ";		
	
	private static final String INSERIR_CLIENTE_COMPRADOR_JURIDICA =
			"INSERT INTO tbClienteComprador ( "+
			"	codCliComprador, "+
			"	cep, "+
			"	numeroEndereco, "+
			"	complementoEndereco, "+
			"	telCliComprador, "+
			"	celCliComprador) "+
			"VALUES (?,?,?,?,?,?); "+
					
			"INSERT INTO tbClienteCompradorJuridica ( "+
       			"	codCliComprador, "+
			"   razaoSocial, "+ 
			"	email,"+
			"	cnpj, "+
			"	inscricaoEstadual, "+
			"	dtFundacao, "+
			"	ramoAtividade)"+
			"VALUES (?,?,?,?,?,?,?)";
	
	private static final String ATUALIZAR_CLIENTE_COMPRADOR_JURIDICA =
			"UPDATE tbClienteComprador SET "+
			"	cep = ?, "+
			"	numeroEndereco = ?, "+
			"	complementoEndereco = ?, "+
			"	telCliComprador = ?, "+
			"	celCliComprador = ? "+
			"WHERE codCliComprador = ?; "+
					
			"UPDATE tbClienteCompradorJuridica SET "+
			//"	codCliComprador = ?, "+
			"	razaoSocial = ?, "+
			"	email,"+
			"	cnpj = ?, "+
			"	inscricaoEstadual = ?, "+
			"	dtFundacao = ?, "+
			"	ramoAtividade = ?, "+
			"WHERE codCliComprador = ?";
	
	/*private static final String VALIDAR_LOGIN_SENHA = 
		"select "+
			"count(id_ClienteComprador) as total " +
		"from "+
			"tbClienteComprador cc "+
		"where "+
			"cc.login_funcionario = ? and " +
			"cc.senha_funcionario = ?";*/
	
	private static final  String CONSULTA_CLIENTE_COMPRADOR_JURIDICA =
			"select * from tbClienteCompradorJuridica order by razaoSocial";

	private static final  String CONSULTA_CLIENTE_COMPRADOR_JURIDICA_NOME =
			"select * from tbClienteCompradorJuridica where razaoSocial like ? order by razaoSocial";
	
	private static final  String CONSULTA_CLIENTE_COMPRADOR_JURIDICA_ID = 
			"select * from tbClienteCompradorJuridica where codCliComprador = ?";
	
	private static final String BUSCAR_NOVO_ID = 
			"SELECT ISNULL(MAX(codCliComprador), 0) + 1 AS NOVO_ID FROM tbClienteComprador";
	
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
	public void inserirClienteCompradorJuridica(ClienteCompradorJuridica cliComp) throws DAOException, SQLException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(INSERIR_CLIENTE_COMPRADOR_JURIDICA);
			statement.setInt(1, getNovoId());
			statement.setString(2, cliComp.getClienteComprador().getCep());
			statement.setInt(3, Integer.parseInt(cliComp.getClienteComprador().getNumeroEndereco()));//pode ser aqui
			statement.setString(4, cliComp.getClienteComprador().getComplementoEndereco());
			statement.setString(5, cliComp.getClienteComprador().getTelefone());
			statement.setString(6, cliComp.getClienteComprador().getCelular());
			
			int codigo = getNovoId();
			statement.setInt(7, codigo);//pega o código que vai ser inserido na tabela pai
			statement.setString(8, cliComp.getRazaoSocial());
			statement.setString(9, cliComp.getEmail());
			statement.setString(10, cliComp.getCnpj());
			statement.setString(11, cliComp.getInscricaoEstadual());
			statement.setDate(12, DbUtil.getSqlDate(cliComp.getDataFundacao()));
			statement.setString(13, cliComp.getRamoAtividade());
					
			statement.executeUpdate();
			conn.commit(); //se tudo ocorrer sem erros ele commita a transação no banco de dados
		}catch(SQLException e){
			conn.rollback(); // caso aconteca algum erro, ele reverte a alteracao no banco de dados
			throw new DAOException(e); //ta apontado aq com erro
		}finally{
			DbUtil.close(conn, statement, result);
		}
	}
	
	//ATUALIZAR	
	public void atualizarClienteCompradorJuridica(ClienteCompradorJuridica cliComp) throws DAOException, SQLException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(ATUALIZAR_CLIENTE_COMPRADOR_JURIDICA);
			statement.setString(1, cliComp.getClienteComprador().getCep());
			statement.setString(2, cliComp.getClienteComprador().getNumeroEndereco());
			statement.setString(3, cliComp.getClienteComprador().getComplementoEndereco());
			statement.setString(4, cliComp.getClienteComprador().getTelefone());
			statement.setString(5, cliComp.getClienteComprador().getCelular());
			statement.setInt(6, cliComp.getClienteComprador().getCodCliComprador());
			
			int codigo = getNovoId();
			statement.setInt(7, codigo);//pega o código que vai ser inserido na tabela pai
			statement.setString(8, cliComp.getRazaoSocial());
			statement.setString(9, cliComp.getCnpj());
			statement.setString(10, cliComp.getInscricaoEstadual());
			statement.setDate(11, DbUtil.getSqlDate(cliComp.getDataFundacao()));
			statement.setString(12, cliComp.getRamoAtividade());
			
			statement.executeUpdate();
			conn.commit(); //se tudo ocorrer sem erros ele commita a transação no banco de dados
		}catch(SQLException e){
			conn.rollback(); // caso aconteca algum erro, ele reverte a alteracao no banco de dados
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
	}
	
	//EXCLUIR
		public void exluirClienteCompradorJuridica(int codigoCliente) throws DAOException, SQLException{
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try{
				statement = conn.prepareStatement(EXCLUIR_CLIENTE_COMPRADOR_JURIDICA);
				statement.setInt(1, codigoCliente);
				statement.setInt(2, codigoCliente);
				
				statement.execute();
				conn.commit(); //se tudo ocorrer sem erros ele commita a transação no banco de dados
			}catch(SQLException e){
				conn.rollback(); // caso aconteca algum erro, ele reverte a alteracao no banco de dados
				throw new DAOException(e);
			}finally{
				DbUtil.close(conn, statement, result);
			}
		}
	
	//CONSULTA NOME
		public ClienteComprador consultaPorCod(int codCliComprador) throws DAOException{
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			ClienteComprador retorno = null;
			try{
				statement = conn.prepareStatement(CONSULTA_CLIENTE_COMPRADOR_JURIDICA_ID);
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