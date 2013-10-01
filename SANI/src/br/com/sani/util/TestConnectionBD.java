package br.com.sani.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.sani.exception.DAOException;

public class TestConnectionBD {
	
	private static final String URL_DATABASE = "jdbc:mysql://192.168.0.13:3306/Odontopro";
	private static final String USER = "root";
	private static final String PASSWORD = "fla8264917";
	private static final String DRIVER_JDBC = "com.mysql.jdbc.Driver";
	
	static {
		try {
			Class.forName(DRIVER_JDBC);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws DAOException{
		try{
			Connection connection = DriverManager.getConnection(URL_DATABASE, USER, PASSWORD);
			JOptionPane.showMessageDialog(null, "Conexão obtida");
			connection.setAutoCommit(false);
			return connection;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Conexão NÃO obtida");
			throw new DAOException(e);
		}
	}
	
	public static void main(String args[]) throws DAOException{
		getConnection();
	}
	
	

}
