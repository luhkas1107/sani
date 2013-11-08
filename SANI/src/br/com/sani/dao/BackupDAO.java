package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.sani.util.DbUtilBackup;

public class BackupDAO {
	
	private static final String BACKUP_NOVO = 
			"backup database SANI to disk=? ";
/*	
	private static final String BACKUP_CLIENTE_COMPRADOR =
			"backup table tbClienteComprador from SANI to disk=?" +
			"backup table tbClienteCompradorFisica from SANI to disk=?" +
			"backup table tbClienteCompradorJuridica from SANI to disk=?";
	
	private static final String BACKUP_CLIENTE_PROPRIETARIO =
			"backup table tbClienteProprietario SANI to disk=?" +
			"backup table tbClienteProprietarioFisica SANI to disk=?" +
			"backup table tbClienteProprietarioJuridica SANI to disk=?";*/
	
	private static final String BACKUP_RESTORE =
			"RESTORE DATABASE SANI FROM DISK=? with replace ";		


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
