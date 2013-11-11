package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sani.bean.ClienteComprador;
import br.com.sani.bean.ClienteCompradorFisica;
import br.com.sani.bean.ClienteCompradorJuridica;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class ClienteCompradorFisicaDAO {

	private static final String EXCLUIR_CLIENTE_COMPRADOR_FISICA = 
			"delete from tbClienteCompradorFisica where codCliComprador = ?; "+
			"delete from tbClienteComprador where codCliComprador = ?; ";
	
	private static final String INSERIR_CLIENTE_COMPRADOR_FISICA =
			"INSERT INTO tbClienteComprador ( "+
			"	codCliComprador, "+
			"	cep, "+
			"	numeroEndereco, "+
			"	complementoEndereco, "+
			"	telCliComprador, "+
			"	celCliComprador) "+
			"VALUES (?,?,?,?,?,?); "+
			
			"INSERT INTO tbClienteCompradorFisica ( "+
			"	codCliComprador, "+
			"	nomePessoa, "+
			"	rgPessoa, "+
			"	cpfPessoa, "+
			"	dtNasc, "+
			"	sexoPessoa, "+
			"	estadoCivilPessoa, "+
			"	renda, "+
			"	profissao, "+
			"	email )"+
			"VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	private static final String ATUALIZAR_CLIENTE_COMPRADOR_FISICA =
			"UPDATE tbClienteComprador SET "+
			"	cep = ?, "+
			"	numeroEndereco = ?, "+
			"	complementoEndereco = ?, "+
			"	telCliComprador = ?, "+
			"	celCliComprador = ? "+
			"WHERE codCliComprador = ?; "+
			
			"UPDATE tbClienteCompradorFisica SET "+
			"	nomePessoa = ?, "+
			"	rgPessoa = ?, "+
			"	cpfPessoa = ?, "+
			"	dtNasc = ?, "+
			"	sexoPessoa = ?, "+
			"	estadoCivilPessoa = ?, "+
			"	renda = ?, "+
			"	profissao = ?, "+
			"	email = ? "+
			"WHERE codCliComprador = ?";
	
	
	private static final  String CONSULTA_CLIENTE_COMPRADOR_FISICA =
			"select * from tbClienteCompradorFisica order by nomePessoa";

	private static final  String CONSULTA_CLIENTE_COMPRADOR_FISICA_NOME =
			"select * from tbClienteCompradorFisica where nomePessoa like ? order by nomePessoa";
	
	private static final  String CONSULTA_CLIENTE_COMPRADOR_FISICA_ID = 
			"select * from tbClienteCompradorFisica where codCliComprador = ?";
	
	private static final String BUSCAR_NOVO_ID = 
			"SELECT ISNULL(MAX(codCliComprador), 0) + 1 AS NOVO_ID FROM tbClienteComprador";
	
	private static final String QUERY_BUSCAR_TODOS = 
			"SELECT "+
			"	C.*, "+
			"	F.*, "+
			"	J.* "+
			"FROM tbClienteComprador C "+
			"		LEFT JOIN tbClienteCompradorFisica F "+
			"	ON C.codCliComprador = F.codCliComprador "+
			"		LEFT JOIN tbClienteCompradorJuridica J "+
			"	ON C.codCliComprador = J.codCliComprador";
	
	private static final String QUERY_BUSCAR_POR_CODIGO = 
			"SELECT "+
			"	C.*, "+
			"	F.*, "+
			"	J.* "+
			"FROM tbClienteComprador C "+
			"		LEFT JOIN tbClienteCompradorFisica F "+
			"	ON C.codCliComprador = F.codCliComprador "+
			"		LEFT JOIN tbClienteCompradorJuridica J "+
			"	ON C.codCliComprador = J.codCliComprador "+
			"WHERE C.codCliComprador = ?";
	
	/**
	 * Esse metodo so serve para consultas que envolvam telas de consulta... consultas
	 * que envolvam edicao de dados devem ser usado outro metodo, que vou explicar dps
	 * @param result
	 * @return
	 * @throws SQLException
	 * @throws DAOException
	 */
	private ClienteComprador getBean(ResultSet result) throws DAOException, SQLException{
		ClienteCompradorFisica cf = new ClienteCompradorFisica();
		ClienteCompradorJuridica cj = new ClienteCompradorJuridica();
		ClienteComprador c = new ClienteComprador();
		
		c.setCodCliComprador(result.getInt("codCliComprador"));
		c.setTelefone(result.getString("telCliComprador"));
		c.setCelular(result.getString("celCliComprador"));
		c.setCep(result.getString("cep"));
		c.setComplementoEndereco(result.getString("complementoEndereco"));
		c.setNumeroEndereco(result.getString("numeroEndereco"));
		c.setTpCliente(result.getString("tpCliente"));
		
		
		if(c.getTpCliente().equals("PF")){
			cf.setNome(result.getString("nomePessoa"));
			cf.setCpf(result.getString("cpfPessoa"));
			cf.setEmail(result.getString("email"));
			cf.setDataNascimento(DbUtil.getJavaDate(result, "dtNasc"));
			cf.setProfissao(result.getString("profissao"));
			cf.setRenda(result.getDouble("renda"));
			cf.setRg(result.getString("rgPessoa"));
			cf.setSexo(result.getString("sexoPessoa"));
			
			cf.setClienteComprador(c);
			
			c.setClienteCompradorFisica(cf);
		}else{
			cj.setRazaoSocial(result.getString("razaoSocial"));
			cj.setEmail(result.getString(21));
			cj.setCnpj(result.getString("cnpj"));
			cj.setDataFundacao(DbUtil.getJavaDate(result, "dtFundacao"));
			cj.setInscricaoEstadual(result.getString("inscricaoEstadual"));
			cj.setRamoAtividade(result.getString("ramoAtividade"));
			
			cj.setClienteComprador(c);
			
			c.setClienteCompradorJuridica(cj);
		}
	
		
		return c;
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
	public void inserirClienteCompradorFisica(ClienteCompradorFisica cliComp) throws DAOException, SQLException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(INSERIR_CLIENTE_COMPRADOR_FISICA);
			statement.setInt(1, getNovoId());
			statement.setString(2, cliComp.getClienteComprador().getCep());
			statement.setString(3, cliComp.getClienteComprador().getNumeroEndereco());
			statement.setString(4, cliComp.getClienteComprador().getComplementoEndereco());
			statement.setString(5, cliComp.getClienteComprador().getTelefone());
			statement.setString(6, cliComp.getClienteComprador().getCelular());
			
			int codigo = getNovoId();
			statement.setInt(7, codigo);//pega o código que vai ser inserido na tabela pai
			statement.setString(8, cliComp.getNome());
			statement.setString(9, cliComp.getRg());
			statement.setString(10, cliComp.getCpf());
			statement.setDate(11, DbUtil.getSqlDate(cliComp.getDataNascimento()));
			statement.setString(12, cliComp.getSexo());
			statement.setString(13, cliComp.getEstadoCivil());
			statement.setDouble(14, cliComp.getRenda());
			statement.setString(15, cliComp.getProfissao());
			statement.setString(16, cliComp.getEmail());
			
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
		public void atualizarClienteCompradorFisica(ClienteCompradorFisica cliComp) throws DAOException, SQLException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(ATUALIZAR_CLIENTE_COMPRADOR_FISICA);
			statement.setString(1, cliComp.getClienteComprador().getCep());
			statement.setString(2, cliComp.getClienteComprador().getNumeroEndereco());
			statement.setString(3, cliComp.getClienteComprador().getComplementoEndereco());
			statement.setString(4, cliComp.getClienteComprador().getTelefone());
			statement.setString(5, cliComp.getClienteComprador().getCelular());
			statement.setInt(6, cliComp.getClienteComprador().getCodCliComprador());
			
			statement.setString(7, cliComp.getNome());
			statement.setString(8, cliComp.getRg());
			statement.setString(9, cliComp.getCpf());
			statement.setDate(10, DbUtil.getSqlDate(cliComp.getDataNascimento()));
			statement.setString(11, cliComp.getSexo());
			statement.setString(12, cliComp.getEstadoCivil());
			statement.setDouble(13, cliComp.getRenda());
			statement.setString(14, cliComp.getProfissao());
			statement.setString(15, cliComp.getEmail());
			statement.setInt(16, cliComp.getClienteComprador().getCodCliComprador());
			
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
	public void exluirClienteCompradorFisica(int codigoCliente) throws DAOException, SQLException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(EXCLUIR_CLIENTE_COMPRADOR_FISICA);
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
	
	
	//metodo pronto
	public List<ClienteComprador> buscarTodos() throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ClienteComprador> retorno = new ArrayList<ClienteComprador>();
		try{
			statement = conn.prepareStatement(QUERY_BUSCAR_TODOS);
			result = statement.executeQuery();
			while(result.next()){
				ClienteComprador temp = getBean(result);
				retorno.add(temp);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}

		return retorno;
	}
	
	public ClienteComprador buscarPorCodigo(int codigo) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		ClienteComprador retorno = null;
		try{
			statement = conn.prepareStatement(QUERY_BUSCAR_POR_CODIGO);
			statement.setInt(1, codigo);
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