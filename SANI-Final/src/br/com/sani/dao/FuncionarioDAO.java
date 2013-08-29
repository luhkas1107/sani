package br.com.sani.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sani.exception.DAOException;
import br.com.sani.bean.Funcionario;
import br.com.sani.util.DbUtil;

public class FuncionarioDAO {

	private static final String EXCLUIR_FUNCIONARIO = 
			"delete from tbFuncionario where id_funcionario = ?";
	
	private static final String INSERIR_FUNCIONARIO =
			"insert into tbFuncionario" +
			"(nome_funcionario," +
			"sexo," +
			"cpf_funcionario," +
			"rg_funcionario," +
			"estadoCivil_funcionario," +
			"nacionalidade_funcionario," +
			"endereco_funcionario," +
			"numeroEndereco_funcionario," +
			"complementoEndereco_funcionario," +
			"cep_funcionario," +
			"telefoneResidencial_funcionario," +
			"telefoneCelular_funcionario," +
			"cargo_funcionario," +
			"login_funcionario," +
			"senha_funcionario," +
			"email_funcionario," +
			"site_funcionario)" +
			"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String ATUALIZAR_FUNCIONARIO =
			"update tbFuncionario set " +
			"nome_funcionario = ? " +
			"sexo = ? " +
			"cpf_funcionario = ? " +
			"rg_funcionario = ? " +
			"estadoCivil_funcionario = ? " +
			"nacionalidade_funcionario = ? " +
			"endereco_funcionario = ? " +
			"numeroEndereco_funcionario = ? " +
			"complementoEndereco_funcionario = ? " +
			"cep_funcionario = ? " +
			"telefoneResidencial_funcionario = ? " +
			"telefoneCelular_funcionario = ? " +
			"cargo_funcionario = ? " +
			"login_funcionario = ? " +
			"senha_funcionario = ? " +
			"email_funcionario = ? " +
			"site_funcionario = ? " +			
			"where idfuncionario = ? ";
	
	private static final String VALIDAR_LOGIN_SENHA = 
		"select "+
			"count(idFuncionario) as total " +
		"from "+
			"tbFuncionario f "+
		"where "+
			"f.login_funcionario = ? and " +
			"f.senha_funcionario = ?";
	
	private static final  String CONSULTA_FUNCIONARIOS =
			"select * from tbFuncionario order by nome_funcionario";

	private static final  String CONSULTA_FUNCIONARIOS_NOME =
			"select * from tbFuncionario where nome_funcionario like ? order by nome_funcionario";
	
	private static final  String CONSULTA_FUNCIONARIO_ID = 
			"select * from tbFuncionario where idfuncionario = ?";
	
	public boolean getAutenticacao(String nome, String senha) throws DAOException {
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		int numReg = 0;
		boolean autenticado = false;
		try {			
			statement = conn.prepareStatement(VALIDAR_LOGIN_SENHA);
			statement.setString(1, nome);
			statement.setString(2, senha);
			result = statement.executeQuery();
			if (result.next()) {
				numReg = result.getInt("total");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		if(numReg != 0){
			return autenticado = true;
		}else{
			return autenticado;			
		}
	}		

	
	public List<Funcionario> consultarFuncionarios(String nome) throws DAOException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Funcionario> listaFunc = new ArrayList<Funcionario>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_FUNCIONARIOS);
			}else{
				statement = conn.prepareStatement(CONSULTA_FUNCIONARIOS_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				Funcionario objFunc = new Funcionario();
				objFunc.setId(result.getInt(1));
				objFunc.setNome(result.getString(2));
				objFunc.setSexo(result.getString(3));
				objFunc.setCpf(result.getString(4));
				objFunc.setRg(result.getString(5));
				objFunc.setEstadoCivil(result.getString(6));
				objFunc.setNacionalidade(result.getString(7));
				objFunc.setEndereco(result.getString(8));
				objFunc.setNumeroEndereco(result.getString(9));
				objFunc.setComplementoEndereco(result.getString(10));
				objFunc.setCep(result.getString(11));
				objFunc.setTelefoneResidencial(result.getString(12));
				objFunc.setTelefoneCelular(result.getString(13));
				objFunc.setCargoFuncionario(result.getString(14));
				objFunc.setLoginFuncionario(result.getString(15));
				objFunc.setSenhaFuncionario(result.getString(16));
				objFunc.setEmailPessoal(result.getString(17));
				objFunc.setSiteFuncionario(result.getString(18));
				
				listaFunc.add(objFunc);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaFunc;		
	}

	public Funcionario consultarFuncionarioID(int idFunc) throws DAOException{		
		Funcionario objFunc = new Funcionario();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_FUNCIONARIO_ID);
			statement.setInt(1, idFunc);
			result = statement.executeQuery();
			while (result.next()) {
				objFunc.setId(result.getInt(1));
				objFunc.setNome(result.getString(2));
				objFunc.setSexo(result.getString(3));
				objFunc.setCpf(result.getString(4));
				objFunc.setRg(result.getString(5));
				objFunc.setEstadoCivil(result.getString(6));
				objFunc.setNacionalidade(result.getString(7));
				objFunc.setEndereco(result.getString(8));
				objFunc.setNumeroEndereco(result.getString(9));
				objFunc.setComplementoEndereco(result.getString(10));
				objFunc.setCep(result.getString(11));
				objFunc.setTelefoneResidencial(result.getString(12));
				objFunc.setTelefoneCelular(result.getString(13));
				objFunc.setCargoFuncionario(result.getString(14));
				objFunc.setLoginFuncionario(result.getString(15));
				objFunc.setSenhaFuncionario(result.getString(16));
				objFunc.setEmailPessoal(result.getString(17));
				objFunc.setSiteFuncionario(result.getString(18));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objFunc;		
	}

	public boolean inserirFuncionario(Funcionario obj) throws DAOException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_FUNCIONARIO);
			statement.setString(1, obj.getNome());
			statement.setString(2, obj.getSexo());
			statement.setString(3, obj.getCpf());
			statement.setString(4, obj.getRg());
			statement.setString(5, obj.getEstadoCivil());
			statement.setString(6, obj.getNacionalidade());
			statement.setString(7, obj.getEndereco());
			statement.setString(8, obj.getNumeroEndereco());
			statement.setString(9, obj.getComplementoEndereco());
			statement.setString(10, obj.getCep());
			statement.setString(11, obj.getTelefoneResidencial());
			statement.setString(12, obj.getTelefoneCelular());
			statement.setString(13, obj.getCargoFuncionario());
			statement.setString(14, obj.getLoginFuncionario());
			statement.setString(15, obj.getSenhaFuncionario());
			statement.setString(16, obj.getEmailPessoal());
			statement.setString(17, obj.getSiteFuncionario());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	
	public boolean atualizarFuncionario(Funcionario objFunc) throws DAOException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_FUNCIONARIO);
			statement.setString(1, objFunc.getNome());
			statement.setString(2, objFunc.getSexo());
			statement.setString(3, objFunc.getCpf());
			statement.setString(4, objFunc.getRg());
			statement.setString(5, objFunc.getEstadoCivil());
			statement.setString(6, objFunc.getNacionalidade());
			statement.setString(7, objFunc.getEndereco());
			statement.setString(8, objFunc.getNumeroEndereco());
			statement.setString(9, objFunc.getComplementoEndereco());
			statement.setString(10, objFunc.getCep());
			statement.setString(11, objFunc.getTelefoneResidencial());
			statement.setString(12, objFunc.getTelefoneCelular());
			statement.setString(13, objFunc.getCargoFuncionario());
			statement.setString(14, objFunc.getLoginFuncionario());
			statement.setString(15, objFunc.getSenhaFuncionario());
			statement.setString(16, objFunc.getEmailPessoal());
			statement.setString(17, objFunc.getSiteFuncionario());			
			statement.setInt(18,objFunc.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirFuncionarios(int idFuncioanrio) throws DAOException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_FUNCIONARIO);
			statement.setInt(1, idFuncioanrio);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}