package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sani.bean.ClienteProprietario;
import br.com.sani.exception.DAOException;
import br.com.sani.util.DbUtil;

public class ClienteProprietarioDAO {

	private static final String EXCLUIR_CLIENTE_PROPRIETARIO = 
			"delete from tbClienteProprietario where nome_ClienteProprietario = ?";
		
		private static final String INSERIR_CLIENTE_PROPRIETARIO =
				"insert into tbClienteProprietario" +
				"(nome_ClienteProprietario," +
				"sexo_ClienteProprietario," +
				"cpf_ClienteProprietario," +
				"rg_ClienteProprietario," +
				"renda_ClienteProprietario," +
				"estadoCivil_ClienteProprietario," +
				"nacionalidade_ClienteProprietario," +
				"endereco_ClienteProprietario," +
				"numeroEndereco_ClienteProprietario," +
				"complementoEndereco_ClienteProprietario," +
				"cep_ClienteProprietario," +
				"telefoneResidencial_ClienteProprietario," +
				"telefoneCelular_ClienteProprietario," +
				"email_ClienteProprietario," +
				"site_ClienteProprietario," +
				"id_ClienteProprietario," +
				"tipoPropriedade_ClienteProprietario)" +
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		private static final String ATUALIZAR_CLIENTE_PROPRIETARIO =
				"update tbClienteProprietario set " +
				"nome_ClienteProprietario = ? " +
				"sexo_ClienteProprietario = ? " +
				"cpf_ClienteProprietario = ? " +
				"rg_ClienteProprietario = ? " +
				"renda_ClienteProprietario = ?" +
				"estadoCivil_ClienteProprietario = ? " +
				"nacionalidade_ClienteProprietario = ? " +
				"endereco_ClienteProprietario = ? " +
				"numeroEndereco_ClienteProprietario = ? " +
				"complementoEndereco_ClienteProprietario = ? " +
				"cep_ClienteProprietario = ? " +
				"telefoneResidencial_ClienteProprietario = ? " +
				"telefoneCelular_ClienteProprietario = ? " +
				"email_ClienteProprietario = ? " +
				"site_ClienteProprietario = ? " +
				"tipoPropriedade_ClienteProprietario = ? " +
				"where id_ClienteProprietario = ? ";
		
		private static final  String CONSULTA_CLIENTE_PROPRIETARIO =
				"select * from tbClienteProprietario order by nome_ClienteProprietario";

		private static final  String CONSULTA_CLIENTE_PROPRIETARIO_NOME =
				"select * from tbClienteProprietario where nome_ClienteProprietario like ? order by nome_ClienteProprietario";
		
		private static final  String CONSULTA_CLIENTE_PROPRIETARIO_ID = 
				"select * from tbClienteProprietario where id_ClienteProprietario = ?";
		
		private static final String CONSULTA_CLIENTE_TODOS = 
			"select * from tbClienteProprietario";
		
		
		private ClienteProprietario getBean(ResultSet result) throws SQLException, DAOException{
			ClienteProprietario objCliProp = new ClienteProprietario();
			objCliProp.setId(result.getInt(""));
			objCliProp.setNome(result.getString(""));
			objCliProp.setSexo(result.getString(""));
			objCliProp.setCpf(result.getString(""));
			objCliProp.setRg(result.getString(""));
			objCliProp.setRenda(result.getString(""));
			objCliProp.setEstadoCivil(result.getString(""));
			objCliProp.setNacionalidade(result.getString(""));
			objCliProp.setEndereco(result.getString(""));
			objCliProp.setNumeroEndereco(result.getString(""));
			objCliProp.setComplementoEndereco(result.getString(""));
			objCliProp.setCep(result.getString(""));
			objCliProp.setTelefoneResidencial(result.getString(""));
			objCliProp.setTelefoneCelular(result.getString(""));
			objCliProp.setEmailPessoal(result.getString(""));
			objCliProp.setSiteClienteProprietario(result.getString(""));
			
			return objCliProp;
		}

		
		public List<ClienteProprietario> consultarClienteProprietario(String idCliProp) throws DAOException{		
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			List<ClienteProprietario> listaCliProp = new ArrayList<ClienteProprietario>();
			try {
				if(idCliProp.equals("")){
					statement = conn.prepareStatement(CONSULTA_CLIENTE_PROPRIETARIO);
				}else{
					statement = conn.prepareStatement(CONSULTA_CLIENTE_PROPRIETARIO_NOME);
					statement.setString(1, "%"+idCliProp+"%");
				}
				result = statement.executeQuery();
				while (result.next()) {
					ClienteProprietario objCliProp = getBean(result);
					listaCliProp.add(objCliProp);
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return listaCliProp;		
		}
		
		public List<ClienteProprietario> consultarTodosClientes() throws DAOException{		
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			List<ClienteProprietario> listaCliProp = new ArrayList<ClienteProprietario>();
			try {
				statement = conn.prepareStatement(CONSULTA_CLIENTE_TODOS);
				result = statement.executeQuery();
				while (result.next()) {
					ClienteProprietario objCliProp = getBean(result);
					listaCliProp.add(objCliProp);
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return listaCliProp;		
		}
		
		

		public ClienteProprietario consultarClienteProprietarioID(int idCliProp) throws DAOException{		
			ClienteProprietario objCliProp = new ClienteProprietario();
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(CONSULTA_CLIENTE_PROPRIETARIO_ID);
				statement.setInt(1, idCliProp);
				result = statement.executeQuery();
				while (result.next()) {
					objCliProp = getBean(result);
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return objCliProp;		
		}

		public boolean inserirClienteProprietario(ClienteProprietario objCliProp) throws DAOException{		
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(INSERIR_CLIENTE_PROPRIETARIO);
				statement.setInt(1, objCliProp.getId());
				statement.setString(2, objCliProp.getNome());
				statement.setString(3, objCliProp.getSexo());
				statement.setString(4, objCliProp.getCpf());
				statement.setString(5, objCliProp.getRg());
				statement.setString(6, objCliProp.getRenda());
				statement.setString(7, objCliProp.getEstadoCivil());
				statement.setString(8, objCliProp.getNacionalidade());
				statement.setString(9, objCliProp.getEndereco());
				statement.setString(10, objCliProp.getNumeroEndereco());
				statement.setString(11, objCliProp.getComplementoEndereco());
				statement.setString(12, objCliProp.getCep());
				statement.setString(13, objCliProp.getTelefoneResidencial());
				statement.setString(14, objCliProp.getTelefoneCelular());
				statement.setString(15, objCliProp.getEmailPessoal());
				statement.setString(16, objCliProp.getSiteClienteProprietario());
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return true;		
		}
		
		public boolean atualizarClienteProprietario(ClienteProprietario objCliProp) throws DAOException{		
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(ATUALIZAR_CLIENTE_PROPRIETARIO);
				statement.setInt(1, objCliProp.getId());
				statement.setString(2, objCliProp.getNome());
				statement.setString(3, objCliProp.getSexo());
				statement.setString(4, objCliProp.getCpf());
				statement.setString(5, objCliProp.getRg());
				statement.setString(6, objCliProp.getRenda());
				statement.setString(7, objCliProp.getEstadoCivil());
				statement.setString(8, objCliProp.getNacionalidade());
				statement.setString(9, objCliProp.getEndereco());
				statement.setString(10, objCliProp.getNumeroEndereco());
				statement.setString(11, objCliProp.getComplementoEndereco());
				statement.setString(12, objCliProp.getCep());
				statement.setString(13, objCliProp.getTelefoneResidencial());
				statement.setString(14, objCliProp.getTelefoneCelular());
				statement.setString(15, objCliProp.getEmailPessoal());
				statement.setString(16, objCliProp.getSiteClienteProprietario());
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return true;		
		}

		public boolean excluirClienteProprietario(int idClienteProprietario) throws DAOException{		
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				statement = conn.prepareStatement(EXCLUIR_CLIENTE_PROPRIETARIO);
				statement.setInt(1, idClienteProprietario);
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return true;		
		}
	}
