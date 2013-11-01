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
			"			en.cep," +
			"			en.endereco," +
			"			cid.cidade," +
			"			cid.uf," +
			"			bai.bairro " +
			"FROM " +
			"		tbEndereco as en " +
			"			inner join tbCidade as cid " +
			"				on en.idCidade = cid.idCidade " +
			"			inner join tbBairro as bai " +
			"				on en.idBairro = bai.idBairro " +
			"WHERE EN.CEP = ?";
	
	private static Endereco getBean(ResultSet result) throws DAOException, SQLException{
		Endereco end = new Endereco();
		
		end.setCep(result.getString("cep"));
		end.setEndereco(result.getString("endereco"));
		end.setBairro(result.getString("bairro"));
		end.setCidade(result.getString("cidade"));
		end.setEstado(result.getString("uf"));
		
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
