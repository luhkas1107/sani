package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.sani.util.DbUtilBackup;

public class BackupDAO {
	
	private static final String BACKUP_NOVO = 
			"backup database venda2 to disk=? ";
	
	private static final String BACKUP_RESTORE =
			"RESTORE DATABASE venda2 FROM DISK=? with replace ";
		


	public static boolean backup_novo(String caminho) throws Exception{		
		Connection conn = DbUtilBackup.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(BACKUP_NOVO);
			statement.setString(1, caminho);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			throw new Exception(e);
		} finally {
			DbUtilBackup.close(conn, statement, result);
		}
		return true;		
	}

	public static boolean backup_restore(String caminho) throws Exception{		
		Connection conn = DbUtilBackup.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(BACKUP_RESTORE);
			statement.setString(1, caminho);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			throw new Exception(e);
		} finally {
			DbUtilBackup.close(conn, statement, result);
		}
		return true;		
	}

	
	
	

}
