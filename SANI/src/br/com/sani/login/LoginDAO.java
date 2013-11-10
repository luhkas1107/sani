package br.com.sani.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class LoginDAO {
	
	private static final String FAZER_LOGIN =
		"select * from tbUsuario where usuario = ? and senha = ?";
	
	
	private Login getBean(ResultSet result) throws DAOException, SQLException{
		Login l = new Login();
		
		l.setEmail("email");
		l.setNome("nome");
		l.setPermissao("permissao");
		l.setSenha("senha");
		l.setUser("usuario");
		
		return l;
	}
	
	
	public Login fazerLogin(String user, String senha) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		Login retorno = null;
		try{
			statement = conn.prepareStatement(FAZER_LOGIN);
			statement.setString(1, user);
			statement.setString(2, senha);
			
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
