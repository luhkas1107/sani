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
	
	
	
	private Object getBean(ResultSet result) throws DAOException, SQLException{
		ClienteProprietarioFisica cf = new ClienteProprietarioFisica();
		ClienteProprietarioJuridica cj = new ClienteProprietarioJuridica();
		ClienteProprietario c = new ClienteProprietario();
		
		Object retorno = new Object();

		c.setCodCliProprietario(result.getInt("codCliProprietario"));
		c.setCelular(result.getString("celCliProprietario"));
		c.setCep(result.getString("cep"));
		c.setComplementoEndereco(result.getString("complementoEndereco"));
		c.setNumeroEndereco(result.getString("numeroEndereco"));
		c.setTelefone(result.getString("telCliProprietario"));
		
		String test = result.getString("razaoSocial");
		
		if(test != null){
			cj.setCnpj(result.getString("cnpj"));
			cj.setDataFundacao(DbUtil.getJavaDate(result, "dtFundacao"));
			cj.setInscEstadual(result.getString("inscricaoEstadual"));
			cj.setRamoAtividade(result.getString("ramoAtividade"));
			cj.setRazaoSocial(test);
			
			cj.setClienteProprietario(c);
			
			retorno = cj;
		}else{
			cf.setCpf(result.getString("cpfPessoa"));
			cf.setDataFalecimento(DbUtil.getJavaDate(result, "dtFalescimento"));
			cf.setDataNascimento(DbUtil.getJavaDate(result, "dtNasc"));
			cf.setEmail(result.getString("email"));
			cf.setEstadoCivil(result.getString("estadoCivilPessoa"));
			cf.setNome(result.getString("nomePessoa"));
			cf.setProfissao(result.getString("profissao"));
			cf.setRenda(result.getDouble("renda"));
			cf.setRg(result.getString("rgPessoa"));
			cf.setSexo(result.getString("sexoPessoa"));
			
			cf.setClienteProprietario(c);
			
			retorno = cf;
		}
		
		
		return retorno;
	}
	
	
	public List<Object> buscarTodos() throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Object> retorno = new ArrayList<Object>();
		try{
			statement = conn.prepareStatement(QUERY_BUSCAR_TODOS);
			result = statement.executeQuery();
			while(result.next()){
				Object temp = getBean(result);
				retorno.add(temp);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
		
		return retorno;
	}
	
	public Object buscarPorId(int codigo) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Object retorno = null;
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
