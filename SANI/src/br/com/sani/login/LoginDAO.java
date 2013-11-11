package br.com.sani.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sani.bean.Proposta;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class LoginDAO {
	
	private static final String FAZER_LOGIN =
		"select * from tbUsuario where usuario = ? and senha = ?";
//		"select * from tbUsuario where cast(usuario as varbinary(50)) = cast( ? as varbinary(50)) and cast(senha as varbinary(50)) = cast( ? as varbinary(50))";
	
	private static final String CRIAR_LOGIN = 
		"insert into tbUsuario ("+
		"usuario,"+
		"senha,"+
		"nome,"+
		"email,"+
		"permissao,)"+
		"VALUES(?,?,?,?,?)";
	
	private Login getBean(ResultSet result) throws DAOException, SQLException{
		Login l = new Login();

		l.setUser("usuario");
		l.setSenha("senha");
		l.setNome("nome");
		l.setEmail("email");
		l.setPermissao("permissao");
				
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
	
	public void inserir(Login l) throws DAOException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = conn.prepareStatement(CRIAR_LOGIN);
			statement.setString(1, l.getUser());
			statement.setString(2, l.getSenha());
			statement.setString(3, l.getNome());
			statement.setString(4, l.getEmail());
			statement.setString(5, l.getPermissao());
			
			statement.executeUpdate();
			conn.commit();
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		}
	}

	

}
