package br.com.sani.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import br.com.sani.exception.*;

public class DbUtil {

	private static final String URL_DATABASE = "jdbc:sqlserver://localhost:1433;databaseName=SANI_BETA;";
	private static final String DRIVER_JDBC = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		private static final String USUARIO_DB = "sa";
	private static final String SENHA_USUARIO_DB = "12345678";
	
	static {
		try {
			Class.forName(DRIVER_JDBC);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws DAOException {
		try {
			Connection connection = DriverManager.getConnection(URL_DATABASE, USUARIO_DB, SENHA_USUARIO_DB);
			connection.setAutoCommit(true);
			return connection;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public static java.util.Date getJavaDate(ResultSet result, String nomeCampo) throws SQLException {
		Date dataFalecimento = result.getDate(nomeCampo);
		java.util.Date javaDate = null;
		if (dataFalecimento != null) {
			javaDate = new Date(dataFalecimento.getTime());
		}
		return javaDate;
	}
	
	public static java.util.Date getStampDate(ResultSet result, String nomeCampo) throws SQLException {
		Date data = new Date(result.getTimestamp(nomeCampo).getTime());
		java.util.Date javaDate = null;
		if (data != null) {
			javaDate = new Date(data.getTime());
		}
		return javaDate;
	}
	
	public static java.sql.Date getSqlDate(java.util.Date date) {
		java.sql.Date sqlDate = null;
		if (date != null) {
			sqlDate = new java.sql.Date(date.getTime());
		}
		return sqlDate;
	}
	
	public static java.sql.Timestamp getSqlTimestamp(java.util.Date date){
		java.sql.Timestamp sqlDate = null;
		if (date != null) {
			sqlDate = new java.sql.Timestamp(date.getTime());
		}
		return sqlDate;
	}
        
    public static byte[] getInputStream(ResultSet result, String campo) throws IOException, SQLException{
        InputStream input = result.getBinaryStream(campo);
        byte[] bytes = new byte[input.available()];
          
        return bytes;
    }
	
	public static void close(Connection conn, Statement statement, ResultSet result) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (result != null) {
				result.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
