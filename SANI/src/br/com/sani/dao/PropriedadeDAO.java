package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sani.bean.ImagemImovel;
import br.com.sani.bean.Propriedade;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;


public class PropriedadeDAO {
		
		private static final String QUERY_SEQUENCIA = 
				"select isnull(max(codPropriedade), 0) + 1 as NOVO_ID from tbPropriedade";
	
		private static final String INSERIR_PROPRIEDADE =
				"INSERT INTO tbPropriedade "+
				"values(?,?,?,?,?,?,?,?)";
		
		private static final String INSERIR_IMAGENS_PROPRIEDADE = 
				"INSERT INTO tbImagensPropriedade "+
				"values(?,?,?)";
		
		
		
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
		
		public int inserirPropriedade(Propriedade p) throws DAOException{
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			int retorno = 0;
			try{
				statement = conn.prepareStatement(INSERIR_PROPRIEDADE);
				retorno = getSequencia();
				statement.setInt(1, retorno);
				statement.setInt(2, p.getClienteProprietario().getCodCliProprietario());
				statement.setString(3, p.getCep());
				statement.setInt(4, p.getNumeroEndereco());
				statement.setString(5, p.getComplementoEndereco());
				statement.setString(6, p.getTipoPropriedade());
				statement.setString(7, p.getSituacaoPropriedade());
				statement.setFloat(8, p.getMetragem());
				
				statement.executeUpdate();
				conn.commit();
			}catch(SQLException e){
				throw new DAOException(e);
			}finally{
				DbUtil.close(conn, statement, result);
			}
			
			return retorno;
		}
		
		public void inserirImagensPropriedade(ImagemImovel img) throws DAOException{
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try{
				statement = conn.prepareStatement(INSERIR_IMAGENS_PROPRIEDADE);
				statement.setInt(1, img.getPropriedade());
				statement.setDate(2, DbUtil.getSqlDate(img.getDataImagem()));
				statement.setBytes(3, img.getImagem());
				
				statement.executeUpdate();
				conn.commit();
			}catch(SQLException e){
				throw new DAOException(e);
			}finally{
				DbUtil.close(conn, statement, result);
			}
		}
		
		
		
}
