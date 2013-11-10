package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sani.bean.ClienteProprietario;
import br.com.sani.bean.Endereco;
import br.com.sani.bean.ImagemImovel;
import br.com.sani.bean.Propriedade;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;


public class PropriedadeDAO {
	
		private static final String QUERY_EXCLUIR = 
				"delete from tbImagensPropriedade where codPropriedade = ?; "+
				"delete from tbProposta where codPropriedade = ?; "+
				"delete from tbPropriedade where codPropriedade = ? ";
		
		private static final String QUERY_SEQUENCIA = 
				"select isnull(max(codPropriedade), 0) + 1 as NOVO_ID from tbPropriedade";
	
		private static final String INSERIR_PROPRIEDADE =
				"INSERT INTO tbPropriedade "+
				"values(?,?,?,?,?,?,?,?)";
		
		private static final String INSERIR_IMAGENS_PROPRIEDADE = 
				"INSERT INTO tbImagensPropriedade "+
				"values(?,?,?)";
		
		private static final String QUERY_BUSCAR_TODOS =
				"SELECT "+
				"	P.*, "+
				"	E.cep, "+
				"	E.endereco, "+
				"	B.bairro, "+
				"	C.cidade, "+
				"	C.uf "+
				"FROM tbPropriedade P "+
				"		INNER JOIN tbEndereco E "+
				"	ON P.cep = E.cep "+
				"		INNER JOIN tbBairro B "+
				"	ON E.idBairro = B.idBairro "+
				"		INNER JOIN tbCidade C "+
				"	ON B.idCidade = C.idCidade ";
		
		private static final String QUERY_BUSCAR_POR_ID =
				"SELECT "+
				"	P.*, "+
				"	E.cep, "+
				"	E.endereco, "+
				"	B.bairro, "+
				"	C.cidade, "+
				"	C.uf "+
				"FROM tbPropriedade P "+
				"		INNER JOIN tbEndereco E "+
				"	ON P.cep = E.cep "+
				"		INNER JOIN tbBairro B "+
				"	ON E.idBairro = B.idBairro "+
				"		INNER JOIN tbCidade C "+
				"	ON B.idCidade = C.idCidade "+
				"WHERE P.codPropriedade = ?";
					
		private Propriedade getBean(ResultSet result) throws DAOException, SQLException{
			Propriedade p = new Propriedade();
			ClienteProprietario cli = new ClienteProprietario();
			Endereco e = new Endereco();
			
			e.setCep(result.getString("cep"));
			e.setEndereco(result.getString("endereco"));
			e.setBairro(result.getString("bairro"));
			e.setCidade(result.getString("cidade"));
			e.setEstado(result.getString("uf"));
			
			cli.setCodCliProprietario(result.getInt("codCliProprietario"));
			p.setClienteProprietario(cli);
			
			p.setCodPropriedade(result.getInt("codPropriedade"));
			p.setComplementoEndereco(result.getString("complementoEndereco"));
			p.setEndereco(e);
			p.setMetragem(result.getFloat("metragemPropriedade"));
			p.setNumeroEndereco(result.getInt("numeroEndereco"));
			p.setSituacaoPropriedade(result.getString("situacaoPropriedade"));
			p.setTipoPropriedade(result.getString("tipoPropriedade"));
			p.setValorPropriedade(result.getFloat("valorPropriedade"));
			
			return p;
		}
		
		
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
		
		public Propriedade buscarPorId(int codigo) throws DAOException{
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			Propriedade retorno = null;
			try{
				statement = conn.prepareStatement(QUERY_BUSCAR_POR_ID);
				statement.setInt(1, codigo);
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
		
		public List<Propriedade> buscarTodos() throws DAOException{
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			List<Propriedade> retorno = new ArrayList<Propriedade>();
			try{
				statement = conn.prepareStatement(QUERY_BUSCAR_TODOS);
				result = statement.executeQuery();
				while(result.next()){
					Propriedade propriedade = getBean(result);
					retorno.add(propriedade);
				}
			}catch(SQLException e){
				throw new DAOException(e);
			}finally{
				DbUtil.close(conn, statement, result);
			}
			
			return retorno;
		}
		
		public void exluir(int codigo) throws DAOException{
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try{
				statement = conn.prepareStatement(QUERY_EXCLUIR);
				statement.setInt(1, codigo);
				statement.setInt(2, codigo);
				statement.setInt(3, codigo);
				
				statement.execute();
				conn.commit();
			}catch(SQLException e){
				throw new DAOException(e);
			}finally{
				DbUtil.close(conn, statement, result);
			}
		}
		
		
}
