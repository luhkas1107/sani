package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sani.bean.Proposta;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class PropostaDAO {
	
	private static final String QUERY_INSERIR = 
		"insert into tbProposta "+
		"values (?,?,?,?,?,?,?,?,?)";
	
	
	public void inserir(Proposta p) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(QUERY_INSERIR);
			statement.setInt(1, p.getCodCliComprador());
			statement.setInt(2, p.getCodPropriedade());
			statement.setDouble(3, p.getValorProposta());
			statement.setDate(4, DbUtil.getSqlDate(p.getValidadeProposta()));
			statement.setString(5, "N");
			statement.setString(6, "A");
			statement.setString(8, p.getFormaPagamento());
			statement.setDate(7, DbUtil.getSqlDate(p.getDataProposta()));
			statement.setString(9, p.getTipoProposta());
			
			statement.executeUpdate();
			conn.commit();
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
	}
	

}
