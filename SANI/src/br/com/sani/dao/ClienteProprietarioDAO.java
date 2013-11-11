package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sani.bean.ClienteProprietario;
import br.com.sani.bean.ClienteProprietarioFisica;
import br.com.sani.bean.ClienteProprietarioJuridica;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class ClienteProprietarioDAO {
	
	private static final String QUERY_BUSCAR_POR_ID = 
		"SELECT "+
		"	C.*, "+
		"	F.*, "+
		"	J.* "+
		"FROM tbClienteProprietario C "+
		"		LEFT JOIN tbClienteProprietarioFisica F "+
		"	ON C.codCliProprietario = F.codCliProprietario "+
		"		LEFT JOIN tbClienteProprietarioJuridica J "+
		"	ON C.codCliProprietario = J.codCliProprietario "+
		"WHERE C.codCliProprietario = ?";
	
	private static final String QUERY_BUSCAR_TODOS = 
		"SELECT "+
		"	C.*, "+
		"	F.*, "+
		"	J.* "+
		"FROM tbClienteProprietario C "+
		"		LEFT JOIN tbClienteProprietarioFisica F "+
		"	ON C.codCliProprietario = F.codCliProprietario "+
		"		LEFT JOIN tbClienteProprietarioJuridica J "+
		"	ON C.codCliProprietario = J.codCliProprietario";	
	
	private static final String QUERY_BUSCAR_POR_NOME = 
		"SELECT "+
		"	C.*, "+
		"	F.*, "+
		"	J.* "+
		"FROM tbClienteProprietario C "+
		"		LEFT JOIN tbClienteProprietarioFisica F "+
		"	ON C.codCliProprietario = F.codCliProprietario "+
		"		LEFT JOIN tbClienteProprietarioJuridica J "+
		"	ON C.codCliProprietario = J.codCliProprietario "+
		"WHERE F.nomePessoa LIKE ? OR J.razaoSocial LIKE ?";	
	
	
	private ClienteProprietario getBean(ResultSet result) throws DAOException, SQLException{
		ClienteProprietarioFisica cf = new ClienteProprietarioFisica();
		ClienteProprietarioJuridica cj = new ClienteProprietarioJuridica();
		ClienteProprietario c = new ClienteProprietario();
		
		c.setCodCliProprietario(result.getInt("codCliProprietario"));
		c.setCelular(result.getString("celCliProprietario"));
		c.setCep(result.getString("cep"));
		c.setComplementoEndereco(result.getString("complementoEndereco"));
		c.setNumeroEndereco(result.getString("numeroEndereco"));
		c.setTelefone(result.getString("telCliProprietario"));
		c.setTpCliente(result.getString("tpCliente"));
		
		if(c.getTpCliente().equals("PJ")){
			cj.setCnpj(result.getString("cnpj"));
			cj.setDataFundacao(DbUtil.getJavaDate(result, "dtFundacao"));
			cj.setInscEstadual(result.getString("inscricaoEstadual"));
			cj.setRamoAtividade(result.getString("ramoAtividade"));
			cj.setRazaoSocial(result.getString("razaoSocial"));
			
			cj.setClienteProprietario(c);
			
			c.setClienteProprietarioJuridica(cj);
		}else{
			cf.setCpf(result.getString("cpfPessoa"));
			cf.setDataFalecimento(DbUtil.getJavaDate(result, "dtFalescimento"));
			cf.setDataNascimento(DbUtil.getJavaDate(result, "dtNasc"));
			cf.setEmail(result.getString("email"));
			cf.setEstadoCivil(result.getString("estadoCivilPessoa"));
			cf.setNome(result.getString("nomePessoa"));
			cf.setRg(result.getString("rgPessoa"));
			cf.setSexo(result.getString("sexoPessoa"));
			
			cf.setClienteProprietario(c);
			
			c.setClienteProprietarioFisica(cf);
		}
		
		
		return c;
	}
	
	
	public List<ClienteProprietario> buscarTodos() throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ClienteProprietario> retorno = new ArrayList<ClienteProprietario>();
		try{
			statement = conn.prepareStatement(QUERY_BUSCAR_TODOS);
			result = statement.executeQuery();
			while(result.next()){
				ClienteProprietario temp = getBean(result);
				retorno.add(temp);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
		
		return retorno;
	}
	
	public List<ClienteProprietario> buscarPorNome(String nome) throws DAOException{
		nome += "%";
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ClienteProprietario> retorno = new ArrayList<ClienteProprietario>();
		try{
			statement = conn.prepareStatement(QUERY_BUSCAR_POR_NOME);
			statement.setString(1, nome);
			statement.setString(2, nome);
			
			result = statement.executeQuery();
			while(result.next()){
				ClienteProprietario temp = getBean(result);
				retorno.add(temp);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
		
		return retorno;
	}
	
	public ClienteProprietario buscarPorId(int codigo) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		ClienteProprietario retorno = null;
		try{
			statement = conn.prepareStatement(QUERY_BUSCAR_POR_ID);
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
