package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sani.exception.DAOException;
import br.com.sani.bean.ClienteComprador;
import br.com.sani.util.DbUtil;

public class ClienteCompradorDAO {

	private static final String EXCLUIR_CLIENTE_COMPRADOR = 
			"delete from tbClienteComprador where nome_ClienteComprador = ?";
	
	private static final String INSERIR_CLIENTE_COMPRADOR =
			"insert into tbClienteComprador" +
			"(nome_ClienteComprador," +
			"sexo_ClienteComprador," +
			"cpf_ClienteComprador," +
			"rg_ClienteComprador," +
			"renda_ClienteComprador," +
			"estadoCivil_ClienteComprador," +
			"nacionalidade_ClienteComprador," +
			"endereco_ClienteComprador," +
			"numeroEndereco_ClienteComprador," +
			"complementoEndereco_ClienteComprador," +
			"cep_ClienteComprador," +
			"telefoneResidencial_ClienteComprador," +
			"telefoneCelular_ClienteComprador," +
			"email_ClienteComprador," +
			"site_ClienteComprador," +
			"id_ClienteComprador)" +
			"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String ATUALIZAR_CLIENTE_COMPRADOR =
			"update tbClienteComprador set " +
			"nome_ClienteComprador = ? " +
			"sexo_ClienteComprador = ? " +
			"cpf_ClienteComprador = ? " +
			"rg_ClienteComprador = ? " +
			"renda_ClienteComprador = ?" +
			"estadoCivil_ClienteComprador = ? " +
			"nacionalidade_ClienteComprador = ? " +
			"endereco_ClienteComprador = ? " +
			"numeroEndereco_ClienteComprador = ? " +
			"complementoEndereco_ClienteComprador = ? " +
			"cep_ClienteComprador = ? " +
			"telefoneResidencial_ClienteComprador = ? " +
			"telefoneCelular_ClienteComprador = ? " +
			"email_ClienteComprador = ? " +
			"site_ClienteComprador = ? " +			
			"where id_ClienteComprador = ? ";
	
	/*private static final String VALIDAR_LOGIN_SENHA = 
		"select "+
			"count(id_ClienteComprador) as total " +
		"from "+
			"tbClienteComprador cc "+
		"where "+
			"cc.login_funcionario = ? and " +
			"cc.senha_funcionario = ?";*/
	
	private static final  String CONSULTA_CLIENTE_COMPRADOR =
			"select * from tbClienteComprador order by nome_ClienteComprador";

	private static final  String CONSULTA_CLIENTE_COMPRADOR_NOME =
			"select * from tbClienteComprador where nome_ClienteComprador like ? order by nome_ClienteComprador";
	
	private static final  String CONSULTA_CLIENTE_COMPRADOR_ID = 
			"select * from tbClienteComprador where id_ClienteComprador = ?";
	
	public List<ClienteComprador> consultarClienteComprador(String idCliComp) throws DAOException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ClienteComprador> listaCliComp = new ArrayList<ClienteComprador>();
		try {
			if(idCliComp.equals("")){
				statement = conn.prepareStatement(CONSULTA_CLIENTE_COMPRADOR);
			}else{
				statement = conn.prepareStatement(CONSULTA_CLIENTE_COMPRADOR_NOME);
				statement.setString(1, "%"+idCliComp+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				ClienteComprador objCliComp = new ClienteComprador();
				objCliComp.setId(result.getInt(1));
				objCliComp.setNome(result.getString(2));
				objCliComp.setSexo(result.getString(3));
				objCliComp.setCpf(result.getString(4));
				objCliComp.setRg(result.getString(5));
				objCliComp.setRenda(result.getString(6));
				objCliComp.setEstadoCivil(result.getString(7));
				objCliComp.setNacionalidade(result.getString(8));
				objCliComp.setEndereco(result.getString(9));
				objCliComp.setNumeroEndereco(result.getString(10));
				objCliComp.setComplementoEndereco(result.getString(11));
				objCliComp.setCep(result.getString(12));
				objCliComp.setTelefoneResidencial(result.getString(13));
				objCliComp.setTelefoneCelular(result.getString(14));
				objCliComp.setEmailPessoal(result.getString(15));
				objCliComp.setSiteClienteComprador(result.getString(16));
				
				listaCliComp.add(objCliComp);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaCliComp;		
	}

	public ClienteComprador consultarClienteCompradorID(int idCliComp) throws DAOException{		
		ClienteComprador objCliComp = new ClienteComprador();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_CLIENTE_COMPRADOR_ID);
			statement.setInt(1, idCliComp);
			result = statement.executeQuery();
			while (result.next()) {
				objCliComp.setId(result.getInt(1));
				objCliComp.setNome(result.getString(2));
				objCliComp.setSexo(result.getString(3));
				objCliComp.setCpf(result.getString(4));
				objCliComp.setRg(result.getString(5));
				objCliComp.setRenda(result.getString(6));
				objCliComp.setEstadoCivil(result.getString(7));
				objCliComp.setNacionalidade(result.getString(8));
				objCliComp.setEndereco(result.getString(9));
				objCliComp.setNumeroEndereco(result.getString(10));
				objCliComp.setComplementoEndereco(result.getString(11));
				objCliComp.setCep(result.getString(12));
				objCliComp.setTelefoneResidencial(result.getString(13));
				objCliComp.setTelefoneCelular(result.getString(14));
				objCliComp.setEmailPessoal(result.getString(15));
				objCliComp.setSiteClienteComprador(result.getString(16));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objCliComp;		
	}

	public boolean inserirClienteComprador(ClienteComprador objCliComp) throws DAOException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_CLIENTE_COMPRADOR);
			statement.setInt(1, objCliComp.getId());
			statement.setString(2, objCliComp.getNome());
			statement.setString(3, objCliComp.getSexo());
			statement.setString(4, objCliComp.getCpf());
			statement.setString(5, objCliComp.getRg());
			statement.setString(6, objCliComp.getRenda());
			statement.setString(7, objCliComp.getEstadoCivil());
			statement.setString(8, objCliComp.getNacionalidade());
			statement.setString(9, objCliComp.getEndereco());
			statement.setString(10, objCliComp.getNumeroEndereco());
			statement.setString(11, objCliComp.getComplementoEndereco());
			statement.setString(12, objCliComp.getCep());
			statement.setString(13, objCliComp.getTelefoneResidencial());
			statement.setString(14, objCliComp.getTelefoneCelular());
			statement.setString(15, objCliComp.getEmailPessoal());
			statement.setString(16, objCliComp.getSiteClienteComprador());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	
	public boolean atualizarClienteComprador(ClienteComprador objCliComp) throws DAOException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_CLIENTE_COMPRADOR);
			statement.setInt(1, objCliComp.getId());
			statement.setString(2, objCliComp.getNome());
			statement.setString(3, objCliComp.getSexo());
			statement.setString(4, objCliComp.getCpf());
			statement.setString(5, objCliComp.getRg());
			statement.setString(6, objCliComp.getRenda());
			statement.setString(7, objCliComp.getEstadoCivil());
			statement.setString(8, objCliComp.getNacionalidade());
			statement.setString(9, objCliComp.getEndereco());
			statement.setString(10, objCliComp.getNumeroEndereco());
			statement.setString(11, objCliComp.getComplementoEndereco());
			statement.setString(12, objCliComp.getCep());
			statement.setString(13, objCliComp.getTelefoneResidencial());
			statement.setString(14, objCliComp.getTelefoneCelular());
			statement.setString(15, objCliComp.getEmailPessoal());
			statement.setString(16, objCliComp.getSiteClienteComprador());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirClienteComprador(int idClienteComprador) throws DAOException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_CLIENTE_COMPRADOR);
			statement.setInt(1, idClienteComprador);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}