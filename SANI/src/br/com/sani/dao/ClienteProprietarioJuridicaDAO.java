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
import br.com.sani.bean.ClienteProprietarioJuridica;
import br.com.sani.bean.Metas;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class ClienteProprietarioJuridicaDAO {

	private static final String EXCLUIR_CLIENTE_PROPRIETARIO_JURIDICA = 
			"delete from tbClienteProprietarioJuridica where codCliProprietario = ?; "+
			"delete from tbClienteProprietario where codCliProprietario = ?; ";		
	
	private static final String INSERIR_CLIENTE_PROPRIETARIO_JURIDICA =
			"INSERT INTO tbClienteProprietario ( "+
			"	codCliProprietario, "+
			"	cep, "+
			"	numeroEndereco, "+
			"	complementoEndereco, "+
			"	telCliProprietario, "+
			"	celCliProprietario, "+
			"	tpCliente) "+
			"VALUES (?,?,?,?,?,?,?); "+
					
			"INSERT INTO tbClienteProprietarioJuridica ( "+
       		"	codCliProprietario, "+
			"   razaoSocial, "+ 
			"	cnpj, "+
			"	inscricaoEstadual, "+
			"	dtFundacao, "+
			"	ramoAtividade)"+
			"VALUES (?,?,?,?,?,?)";
	
	private static final String ATUALIZAR_CLIENTE_PROPRIETARIO_JURIDICA =
			"UPDATE tbClienteProprietario SET "+
			"	cep = ?, "+
			"	numeroEndereco = ?, "+
			"	complementoEndereco = ?, "+
			"	telCliProprietario = ?, "+
			"	celCliProprietario = ? "+
			"WHERE codCliProprietario = ?; "+
					
			"UPDATE tbClienteProprietarioJuridica SET "+
			"	razaoSocial = ?, "+
			"	cnpj = ?, "+
			"	inscricaoEstadual = ?, "+
			"	dtFundacao = ?, "+
			"	ramoAtividade = ? "+
			"WHERE codCliProprietario = ?";
	
	
	private static final String BUSCAR_NOVO_ID = 
			"SELECT ISNULL(MAX(codCliProprietario), 0) + 1 AS NOVO_ID FROM tbClienteProprietario";
	
	
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
	public void inserirClienteProprietarioJuridica(ClienteProprietarioJuridica cliProp) throws DAOException, SQLException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(INSERIR_CLIENTE_PROPRIETARIO_JURIDICA);
			statement.setInt(1, getNovoId());
			statement.setString(2, cliProp.getClienteProprietario().getCep());
			statement.setInt(3, Integer.parseInt(cliProp.getClienteProprietario().getNumeroEndereco()));//pode ser aqui
			statement.setString(4, cliProp.getClienteProprietario().getComplementoEndereco());
			statement.setString(5, cliProp.getClienteProprietario().getTelefone());
			statement.setString(6, cliProp.getClienteProprietario().getCelular());
			statement.setString(7, "PJ");
			
			int codigo = getNovoId();
			statement.setInt(8, codigo);//pega o código que vai ser inserido na tabela pai
			statement.setString(9, cliProp.getRazaoSocial());
			statement.setString(10, cliProp.getCnpj());
			statement.setString(11, cliProp.getInscEstadual());
			statement.setDate(12, DbUtil.getSqlDate(cliProp.getDataFundacao()));
			statement.setString(13, cliProp.getRamoAtividade());
					
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
	public void atualizarClienteProprietarioJuridica(ClienteProprietarioJuridica cliprop) throws DAOException, SQLException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(ATUALIZAR_CLIENTE_PROPRIETARIO_JURIDICA);
			statement.setString(1, cliprop.getClienteProprietario().getCep());
			statement.setString(2, cliprop.getClienteProprietario().getNumeroEndereco());
			statement.setString(3, cliprop.getClienteProprietario().getComplementoEndereco());
			statement.setString(4, cliprop.getClienteProprietario().getTelefone());
			statement.setString(5, cliprop.getClienteProprietario().getCelular());
			statement.setInt(6, cliprop.getClienteProprietario().getCodCliProprietario());
			
			int codigo = getNovoId();
			statement.setInt(7, codigo);//pega o código que vai ser inserido na tabela pai
			statement.setString(8, cliprop.getRazaoSocial());
			statement.setString(9, cliprop.getCnpj());
			statement.setString(10, cliprop.getInscEstadual());
			statement.setDate(11, DbUtil.getSqlDate(cliprop.getDataFundacao()));
			statement.setString(12, cliprop.getRamoAtividade());
			
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
		public void exluirClienteProprietarioJuridica(int codigoCliente) throws DAOException, SQLException{
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try{
				statement = conn.prepareStatement(EXCLUIR_CLIENTE_PROPRIETARIO_JURIDICA);
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
	
}