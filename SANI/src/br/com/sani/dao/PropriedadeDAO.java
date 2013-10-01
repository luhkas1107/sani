package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sani.bean.ClienteProprietario;
import br.com.sani.bean.Propriedade;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class PropriedadeDAO {

	private static final String EXCLUIR_PROPRIEDADE = 
			"delete from tbPropriedades where idPropriedade = ?";
		
		private static final String INSERIR_PROPRIEDADE =
				"insert into tbPropriedades" +
				"tipoPropriedadeComercial," +
				"tipoPropriedadeResidencial," +
				"estado_imovel," +
				"nome_proprietario," +
				"cpf_proprietario," +
				"email_proprietario," +
				"endereco_propriedade," +
				"cep_propriedade," +
				"numero_propriedade," +
				"complemento_propriedade," +
				"metragemPropriedade," +
				"idPropriedade)" +
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		private static final String ATUALIZAR_PROPRIEDADE =
				"update tbPropriedades set " +
				"tipoPropriedadeComercial = ? " +
				"tipoPropriedadeResidencial = ? " +
				"estado_imovel = ? " +
				"nome_proprietario = ? " +
				"cpf_proprietario = ?" +
				"email_proprietario = ? " +
				"endereco_propriedade = ? " +
				"cep_propriedade = ? " +
				"numero_propriedade = ? " +
				"complemento_propriedade = ? " +
				"metragemPropriedade = ? " +
				"where idPropriedade = ? ";
				
		private static final  String CONSULTA_PROPRIEDADE =
				"select * from tbPropriedades order by idPropriedade";

		private static final  String CONSULTA_PROPRIEDADE_ENDERECO =
				"select * from tbPropriedades where endereco_propriedade like ? order by endereco_propriedade";
		
		private static final  String CONSULTA_PROPRIEDADE_ID = 
				"select * from tbPropriedades where idPropriedade = ?";
		
		public List<Propriedade> consultarPropriedade(String idPropriedade) throws DAOException{		
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			List<Propriedade> listaCliProp = new ArrayList<Propriedade>();
			try {
				if(idPropriedade.equals("")){
					statement = conn.prepareStatement(CONSULTA_PROPRIEDADE);
				}else{
					statement = conn.prepareStatement(CONSULTA_PROPRIEDADE_ID);
					statement.setString(1, "%"+idPropriedade+"%");
				}
				result = statement.executeQuery();
				while (result.next()) {
					Propriedade objPropriedade = new Propriedade();
					objPropriedade.setIdPropriedade(result.getInt(1));
					objPropriedade.setPropriedadeComercial(result.getString(2));
					objPropriedade.setPropriedadeResidencial(result.getString(3));
					objPropriedade.setEstadoImovel(result.getString(4));
					objPropriedade.setEnderecoPropriedade(result.getString(5));
					objPropriedade.setCepPropriedade(result.getString(6));
					objPropriedade.setNumeroPropriedade(result.getString(7));
					objPropriedade.setComplementoPropriedade(result.getString(8));
					objPropriedade.setMetragemPropriedade(result.getString(9));
					
					listaCliProp.add(objPropriedade);
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return listaCliProp;		
		}

		public Propriedade consultarPropriedadeID(int idPropriedade) throws DAOException{		
			Propriedade objPropriedade = new Propriedade();
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(CONSULTA_PROPRIEDADE_ENDERECO);
				statement.setInt(1, idPropriedade);
				result = statement.executeQuery();
				while (result.next()) {
					objPropriedade.setIdPropriedade(result.getInt(1));
					objPropriedade.setPropriedadeComercial(result.getString(2));
					objPropriedade.setPropriedadeResidencial(result.getString(3));
					objPropriedade.setEstadoImovel(result.getString(4));
					objPropriedade.setEnderecoPropriedade(result.getString(5));
					objPropriedade.setCepPropriedade(result.getString(6));
					objPropriedade.setNumeroPropriedade(result.getString(7));
					objPropriedade.setComplementoPropriedade(result.getString(8));
					objPropriedade.setMetragemPropriedade(result.getString(9));
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return objPropriedade;		
		}

		public boolean inserirPropriedade(ClienteProprietario objPropriedade) throws DAOException{		
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(INSERIR_PROPRIEDADE);
				statement.setInt(1, objPropriedade.getId());
				statement.setString(2, objPropriedade.getNome());
				statement.setString(3, objPropriedade.getSexo());
				statement.setString(4, objPropriedade.getCpf());
				statement.setString(5, objPropriedade.getRg());
				statement.setString(6, objPropriedade.getRenda());
				statement.setString(7, objPropriedade.getEstadoCivil());
				statement.setString(8, objPropriedade.getNacionalidade());
				statement.setString(9, objPropriedade.getEndereco());
				statement.setString(10, objPropriedade.getNumeroEndereco());
				statement.setString(11, objPropriedade.getComplementoEndereco());
				statement.setString(12, objPropriedade.getCep());
				statement.setString(13, objPropriedade.getTelefoneResidencial());
				statement.setString(14, objPropriedade.getTelefoneCelular());
				statement.setString(15, objPropriedade.getEmailPessoal());
				statement.setString(16, objPropriedade.getSiteClienteProprietario());
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return true;		
		}
		
		public boolean atualizarPropriedade(ClienteProprietario objPropriedade) throws DAOException{		
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(ATUALIZAR_PROPRIEDADE);
				statement.setInt(1, objPropriedade.getId());
				statement.setString(2, objPropriedade.getNome());
				statement.setString(3, objPropriedade.getSexo());
				statement.setString(4, objPropriedade.getCpf());
				statement.setString(5, objPropriedade.getRg());
				statement.setString(6, objPropriedade.getRenda());
				statement.setString(7, objPropriedade.getEstadoCivil());
				statement.setString(8, objPropriedade.getNacionalidade());
				statement.setString(9, objPropriedade.getEndereco());
				statement.setString(10, objPropriedade.getNumeroEndereco());
				statement.setString(11, objPropriedade.getComplementoEndereco());
				statement.setString(12, objPropriedade.getCep());
				statement.setString(13, objPropriedade.getTelefoneResidencial());
				statement.setString(14, objPropriedade.getTelefoneCelular());
				statement.setString(15, objPropriedade.getEmailPessoal());
				statement.setString(16, objPropriedade.getSiteClienteProprietario());
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return true;		
		}

		public boolean excluirPropriedade(int idPropriedade) throws DAOException{		
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(EXCLUIR_PROPRIEDADE);
				statement.setInt(1, idPropriedade);
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return true;		
		}
}
