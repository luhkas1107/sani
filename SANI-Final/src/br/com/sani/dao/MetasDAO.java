package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sani.bean.Metas;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class MetasDAO {
	
	private static final String QUERY_SEQUENCIA = 
		"SELECT ISNULL(MAX(COD_META), 0) + 1 AS NOVO_ID FROM tbMetas";
	
	private static final String QUERY_INSERIR = 
		"INSERT INTO tbMetas (COD_META, DESC_META, DI_META, DF_META, ST_META) "+
		"VALUES (?,?,?,?,?)";
	
	private static final String QUERY_ATUALIZAR = 
		"UPDATE tbMetas "
		+ "SET	DESC_META = ?, "
		+ "		DI_META = ?, "
		+ "		DF_META = ? "
		+"WHERE COD_META = ?";
	
	private static final String QUERY_FINALIZAR_META = 
		"UPDATE tbMetas SET ST_META = 'F' WHERE COD_META = ?";
	
	private static final String QUERY_BUSCAR_TODOS = 
		"SELECT COD_META, "
		+ "		DESC_META,"
		+ "		DI_META,"
		+ "		DF_META,"
		+ "		ST_META"
		+"WHERE NOT ST_META = 'F' ";
	
	private static final String QUERY_BUSCAR_POR_ID =
		"SELECT COD_META, "
		+ "		DESC_META,"
		+ "		DI_META,"
		+ "		DF_META,"
		+ "		ST_META"
		+"WHERE NOT ST_META = 'F' AND COD_META = ?";
	
	private static final String QUERY_BUSCAR_POR_DATA = 
		"SELECT COD_META, "
		+ "		DESC_META,"
		+ "		DI_META,"
		+ "		DF_META,"
		+ "		ST_META"
		+"WHERE NOT ST_META = 'F' AND DI_META = ?";
	
	private static final String QUERY_BUSCAR_POR_ENTRE_DATAS =
		"SELECT COD_META, "
		+ "		DESC_META,"
		+ "		DI_META,"
		+ "		DF_META,"
		+ "		ST_META"
		+"WHERE NOT ST_META = 'F' AND DI_META BETWEEN ? AND ?";
	
	private Metas getBean(ResultSet result) throws SQLException, DAOException{
		Metas metas = new Metas();
		
		metas.setIdMeta(result.getInt("COD_META"));
		metas.setDescrMeta(result.getString("DESC_META"));
		metas.setDataInicio(DbUtil.getJavaDate(result, "DI_META"));
		metas.setDataFim(DbUtil.getJavaDate(result, "DF_META"));
		metas.setStMeta(result.getString("ST_META"));
		
		return metas;
	}
	
	/** getSequencia
	 * Método que busca o próximo id para ser usando antes de inserir no banco de dados
	 * (substitui) o identity!!! Transões!!
	 * @return
	 * @throws DAOException
	 */
	public int getSequencia() throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		int retorno = 0;
		try{
			statement = conn.prepareStatement(QUERY_SEQUENCIA);
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
	
	public void inserir(Metas metas) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(QUERY_INSERIR);
			statement.setInt(1, getSequencia());
			statement.setString(2, metas.getDescrMeta());
			statement.setDate(3, DbUtil.getSqlDate(metas.getDataInicio()));
			statement.setDate(4, DbUtil.getSqlDate(metas.getDataFim()));
			statement.setString(5, "A");
			
			statement.executeUpdate();
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
	}
	
	public void atualizar(Metas metas) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(QUERY_ATUALIZAR);
			statement.setString(1, metas.getDescrMeta());
			statement.setDate(2, DbUtil.getSqlDate(metas.getDataInicio()));
			statement.setDate(3, DbUtil.getSqlDate(metas.getDataFim()));
			statement.setInt(4, metas.getIdMeta());
			
			statement.executeUpdate();
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
	}
	
	public void finalizarMeta(int idMeta) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(QUERY_FINALIZAR_META);
			statement.setInt(1, idMeta);
			
			statement.executeUpdate();
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
	}
	
	public List<Metas> buscarTodos() throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Metas> retorno = new ArrayList<Metas>();
		try{
			statement = conn.prepareStatement(QUERY_BUSCAR_TODOS);
			result = statement.executeQuery();
			while(result.next()){
				Metas m = getBean(result);
				retorno.add(m);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
		
		return retorno;
	}
	
	public Metas buscarPorId(int idMeta) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Metas retorno = null;
		try{
			statement = conn.prepareStatement(QUERY_BUSCAR_POR_ID);
			statement.setInt(1, idMeta);
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
	
	public List<Metas> buscarPorData(Date dataInicio) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Metas> retorno = new ArrayList<Metas>();
		try{
			statement = conn.prepareStatement(QUERY_BUSCAR_POR_DATA);
			statement.setDate(1, DbUtil.getSqlDate(dataInicio));
			result = statement.executeQuery();
			while(result.next()){
				Metas m = getBean(result);
				retorno.add(m);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
		
		return retorno;
	}
	
	public List<Metas> buscarPorEntreData(Date dataInicio, Date dataFim) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Metas> retorno = new ArrayList<Metas>();
		try{
			statement = conn.prepareStatement(QUERY_BUSCAR_POR_ENTRE_DATAS);
			statement.setDate(1, DbUtil.getSqlDate(dataInicio));
			statement.setDate(2, DbUtil.getSqlDate(dataFim));
			result = statement.executeQuery();
			while(result.next()){
				Metas m = getBean(result);
				retorno.add(m);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
		
		return retorno;
	}

}








