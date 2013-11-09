package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.sani.bean.ClienteCompradorFisica;
import br.com.sani.bean.ClienteProprietario;
import br.com.sani.bean.ClienteProprietarioFisica;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class ClienteProprietarioFisicaDAO {

	private static final String EXCLUIR_CLIENTE_PROPRIETARIO_FISICA = 
			"delete from tbClienteProprietarioFisica where codCliProprietario = ?; "+
			"delete from TB_CLIENTE_PROPRIETARIO where codCliProprietario = ?; ";
		
	private static final String INSERIR_CLIENTE_PROPRIETARIO_FISICA =
			"INSERT INTO tbClienteProprietario ( "+
			"	codCliProprietario, "+
			"	cep, "+
			"	numeroEndereco, "+
			"	complementoEndereco, "+
			"	telCliProprietario, "+
			"	celCliProprietario) "+
			"VALUES (?,?,?,?,?,?); "+
						
			"INSERT INTO tbClienteProprietarioFisica ( "+
			"	codCliProprietario, "+
			"	nomePessoa, "+
			"	rgPessoa, "+
			"	cpfPessoa, "+
			"	dtNasc, "+
			"	dtFalescimento, "+
			"	sexoPessoa, "+ 
			"	estadoCivilPessoa, "+
			"	email )"+
			"VALUES (?,?,?,?,?,?,?,?,?)";
		
	private static final String ATUALIZAR_CLIENTE_PROPRIETARIO_FISICA =
			"UPDATE tbClienteProprietario SET "+
			"	cep = ?, "+
			"	numeroEndereco = ?, "+
			"	complementoEndereco = ?, "+
			"	telCliProprietario = ?, "+
			"	celCliProprietario = ? "+
			"WHERE codCliProprietario = ?; "+
			
			"UPDATE tbClienteProprietarioFisica SET "+
			"	nomePessoa = ?, "+
			"	rgPessoa = ?, "+
			"	cpfPessoa = ?, "+
			"	dtNasc = ?, "+
			"	sexoPessoa = ?, "+
			"	estadoCivilPessoa = ?, "+
			"	renda = ?, "+
			"	profissao = ?, "+
			"	email = ? "+
			"WHERE codCliProprietario = ?";
	
	private static final  String CONSULTA_CLIENTE_PROPRIETARIO_FISICA =
			"select * from tbClienteProprietarioFisica order by nomePessoa";
	
	private static final  String CONSULTA_CLIENTE_PROPRIETARIO_FISICA_NOME =
			"select * from tbClienteProprietarioFisica where nomePessoa like ? order by nomePessoa";
		
	private static final  String CONSULTA_CLIENTE_PROPRIETARIO_FISICA_ID = 
			"select * from tbClienteProprietarioFisica where codCliProprietario = ?";
		
	private static final String CONSULTA_CLIENTE_TODOS = 
			"select * from tbClienteProprietarioFisica";
	
	private static final String BUSCAR_NOVO_ID = 
			"SELECT ISNULL(MAX(codCliProprietario), 0) + 1 AS NOVO_ID FROM tbClienteProprietario";
	
		

	private ClienteProprietario getBean(ResultSet result) throws SQLException, DAOException{
		ClienteProprietario cliComp = new ClienteProprietario();
		
		cliComp.setCodCliProprietario(result.getInt(""));
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
	public void inserirClienteProprietarioFisica(ClienteProprietarioFisica cliComp) throws DAOException, SQLException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(INSERIR_CLIENTE_PROPRIETARIO_FISICA);
			statement.setInt(1, getNovoId());
			statement.setString(2, cliComp.getCodCliProprietario().getCep());
			statement.setInt(3, Integer.parseInt(cliComp.getCodCliProprietario().getNumeroEndereco()));
			statement.setString(4, cliComp.getCodCliProprietario().getComplementoEndereco());
			statement.setString(5, cliComp.getCodCliProprietario().getTelefone());
			statement.setString(6, cliComp.getCodCliProprietario().getCelular());
			
			int codigo = getNovoId();
			statement.setInt(7, codigo);//pega o código que vai ser inserido na tabela pai
			statement.setString(8, cliComp.getNome());
			statement.setString(9, cliComp.getRg());
			statement.setString(10, cliComp.getCpf());
			statement.setDate(11, DbUtil.getSqlDate(cliComp.getDataNascimento()));
			statement.setDate(12, DbUtil.getSqlDate(cliComp.getDataFalecimento()));
			statement.setString(13, cliComp.getSexo());
			statement.setString(14, cliComp.getEstadoCivil());
			statement.setString(15, cliComp.getEmail());
			
			statement.executeUpdate();
			conn.commit(); //se tudo ocorrer sem erros ele commita a transação no banco de dados
		}catch(SQLException e){
			conn.rollback(); // caso aconteca algum erro, ele reverte a alteracao no banco de dados
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
	}
	
	//ATUALIZAR
		public void atualizarClienteProprietarioFisica(ClienteProprietarioFisica cliComp) throws DAOException, SQLException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try{
			statement = conn.prepareStatement(ATUALIZAR_CLIENTE_PROPRIETARIO_FISICA);
			statement.setString(1, cliComp.getCodCliProprietario().getCep());
			statement.setString(2, cliComp.getCodCliProprietario().getNumeroEndereco());
			statement.setString(3, cliComp.getCodCliProprietario().getComplementoEndereco());
			statement.setString(4, cliComp.getCodCliProprietario().getTelefone());
			statement.setString(5, cliComp.getCodCliProprietario().getCelular());
			statement.setInt(6, cliComp.getCodCliProprietario().getCodCliProprietario());
			
			statement.setString(7, cliComp.getNome());
			statement.setString(8, cliComp.getRg());
			statement.setString(9, cliComp.getCpf());
			statement.setDate(10, DbUtil.getSqlDate(cliComp.getDataNascimento()));
			statement.setString(11, cliComp.getSexo());
			statement.setString(12, cliComp.getEstadoCivil());
			statement.setDouble(13, cliComp.getRenda());
			statement.setString(14, cliComp.getProfissao());
			statement.setString(15, cliComp.getEmail());
			statement.setInt(16, cliComp.getCodCliProprietario().getCodCliProprietario());
			
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
	public void exluirClienteProprietarioFisica(int codigoCliente) throws DAOException, SQLException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(EXCLUIR_CLIENTE_PROPRIETARIO_FISICA);
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
	

	public ClienteProprietario consultarTodosClientes() throws DAOException {
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		ClienteProprietario retorno = null;
		try{
			statement = conn.prepareStatement(CONSULTA_CLIENTE_TODOS);
			//statement.setInt(1, );
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
	
	public ClienteProprietario consultarClienteProprietarioID(int codCliProprietario) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		ClienteProprietario retorno = null;
		try{
			statement = conn.prepareStatement(CONSULTA_CLIENTE_PROPRIETARIO_FISICA_ID);
			statement.setInt(1, codCliProprietario);
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