package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sani.bean.Endereco;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class EnderecoDAO {
	
	private static final String QUERY_BUSCAR_POR_CEP=
			"SELECT " +
			"			EN.CEP," +
			"			EN.ENDERECO," +
			"			CID.CIDADE," +
			"			CID.UF," +
			"			BAI.BAIRRO " +
			"FROM " +
			"		tbEndereco as EN " +
			"			inner join tbCidade as CID " +
			"				on EN.ID_CIDADE = CID.ID_CIDADE " +
			"			inner join tbBairro as BAI " +
			"				on EN.ID_BAIRRO = BAI.ID_BAIRRO " +
			"WHERE EN.CEP = ?";
	
	private static Endereco getBean(ResultSet result) throws DAOException, SQLException{
		Endereco end = new Endereco();
		
		end.setCep(result.getString("CEP"));
		end.setEndereco(result.getString("ENDERECO"));
		end.setBairro(result.getString("BAIRRO"));
		end.setCidade(result.getString("CIDADE"));
		end.setUf(result.getString("UF"));
		
		return end;
		
	}
	
	public Endereco buscarPorCep(String cep) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Endereco end = null;
		try{
			statement = conn.prepareStatement(QUERY_BUSCAR_POR_CEP);
			statement.setString(1, cep);
			result = statement.executeQuery();
			while(result.next()){
				end = getBean(result);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}
		finally{
			DbUtil.close(conn, statement, result);
		}
		return end;
	}

}
