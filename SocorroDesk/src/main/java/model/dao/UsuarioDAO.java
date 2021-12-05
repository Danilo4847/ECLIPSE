package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class UsuarioDAO {

	DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public UsuarioVO realizarLoginDAO(UsuarioVO usuarioVO) {

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String query = "SELECT u.idusuario, tipo.descricao, u.nome, u.cpf, "
				+ "u.email, u.datacadastro, u.dataexpiracao " + "FROM usuario u, tipousuario tipo " + "WHERE u.login='"
				+ usuarioVO.getLogin() + "' " + "AND u.senha = '" + usuarioVO.getSenha() + "' "
				+ "AND u.idtipousuario=tipo.idtipousuario";

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				usuarioVO.setIdusuario(Integer.parseInt(resultado.getString(1)));
				usuarioVO.setTipoUsuarioVO(TipoUsuarioVO.valueOf(resultado.getString(2)));
				usuarioVO.setNome(resultado.getString(3));
				usuarioVO.setCpf(resultado.getString(4));
				usuarioVO.setEmail(resultado.getString(5));
				usuarioVO.setDataCadastro(LocalDate.parse(resultado.getString(6), formaterDate));
				if (resultado.getString(7) != null) {
					usuarioVO.setDataExpiracao(LocalDate.parse(resultado.getString(7), formaterDate));
				}

			}
		} catch (SQLException e) {

			System.out.println("Erro ao executar a query que realiza o login");
			System.out.println("Erro" + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return usuarioVO;
	}

	public ArrayList<TipoUsuarioVO> consultarTipousuarioDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<TipoUsuarioVO> listaTipoUsuarioVO = new ArrayList<TipoUsuarioVO>();

		String query = "SELECT descricao FROM tipousuario";

		try {
			resultado = stmt.executeQuery(query);

			while (resultado.next()) {
				TipoUsuarioVO tipoUsuarioVO = TipoUsuarioVO.valueOf(resultado.getString(1));
				listaTipoUsuarioVO.add(tipoUsuarioVO);
			}

		} catch (SQLException e) {
			System.out.println("erro ao executar a query de consulta dos tipos de usuário");
			System.out.println("erro" + getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return listaTipoUsuarioVO;

	}

	private String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean verificarExistenciaRegistroPorCpfDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;

		ArrayList<TipoUsuarioVO> listaTipoUsuarioVO = new ArrayList<TipoUsuarioVO>();

		String query = "SELECT cpf FROM usuario WHERE cpf = '" + usuarioVO.getCpf() + "'";

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				retorno = true;
			}

			while (resultado.next()) {
				TipoUsuarioVO tipoUsuarioVO = TipoUsuarioVO.valueOf(resultado.getString(1));
				listaTipoUsuarioVO.add(tipoUsuarioVO);
			}

		} catch (SQLException e) {
			System.out.println("erro ao executar a query de consulta dos tipos de usuário");
			System.out.println("erro" + getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return retorno;
	}

	public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		String query = "INSERT INTO usuario (idtipousuario, nome, cpf, email, datacadastro, login, senha) VALUES(?,?,?,?,?,?,?)";

		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {
			pstmt.setInt(1, usuarioVO.getTipoUsuarioVO().getValor());
			pstmt.setString(2, usuarioVO.getNome());
			pstmt.setString(3, usuarioVO.getCpf());
			pstmt.setString(4, usuarioVO.getEmail());
			pstmt.setObject(5, usuarioVO.getDataCadastro());
			pstmt.setString(6, usuarioVO.getLogin());
			pstmt.setString(7, usuarioVO.getSenha());
			pstmt.execute();

			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				usuarioVO.setIdusuario(resultado.getInt(1));

			}

		} catch (SQLException e) {
			System.out.println("erro ao executar a query de consulta dos tipos de usuário");
			System.out.println("erro" + getMessage());
		} finally {

			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return usuarioVO;

	}

	public boolean verificarExistenciaRegistroPorIdUsuarioDAO(int idusuario) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;

		String query = "SELECT idusuario FROM usuario WHERE idusuario = " + idusuario;

		try {
			resultado = stmt.executeQuery(query);

			if (resultado.next()) {
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar verificação de existencia de registro do usuario");
			System.out.println("Erro" + e.getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closePreparedStatement(stmt);
			Banco.closeResultSet(resultado);
		}
		return retorno;
	}

	public boolean verificarDesligamentoPorIdUsuarioDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;

		String query = "SELECT idusuario FROM usuario WHERE idusuario = " + usuarioVO.getIdusuario()
				+ " AND dataexpiracao is not null";

		try {

			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar de verfifcação do usuario");
			System.out.println("Erro" + e.getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closePreparedStatement(stmt);
			Banco.closeResultSet(resultado);
		}

		return retorno;

	}

	public boolean excluirUsuarioDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;

		String query = "UPDATE usuario SET dataexpiracao = '" + usuarioVO.getDataExpiracao() + "'"
				+ " WHERE idusuario = " + usuarioVO.getIdusuario();

		try {
			if (stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de exclusão do usuario");
			System.out.println("Erro" + e.getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closePreparedStatement(stmt);
		}

		return retorno;

	}

	public boolean atualizarUsuarioDAO(UsuarioVO usuarioVO) {

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;

		String query = "UPDATE usuario SET idtipousuario = " + usuarioVO.getTipoUsuarioVO().getValor() + ", nome = '"
				+ usuarioVO.getNome() + "', cpf = '" + usuarioVO.getCpf() + "', email= '" + usuarioVO.getEmail()
				+ "', datacadastro = '" + usuarioVO.getDataCadastro() + "', login= '" + usuarioVO.getLogin()
				+ "', senha = '" + usuarioVO.getSenha() + "' WHERE idusuario =" + usuarioVO.getIdusuario();

		try {
			if (stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de atualização do usuario");
			System.out.println("Erro" + e.getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closePreparedStatement(stmt);
		}

		return retorno;

	}

	public ArrayList<UsuarioVO> consultarTodosOsUsuariosDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<UsuarioVO> listausuarioVo = new ArrayList<UsuarioVO>();

		String query = "SELECT u.idusuario, u.descricao, u.nome, u.cpf, u.email, u.dataCadstro, u.dataExpericao, u.login, u.senha"
				+ "FROM usuario u, tipoUsuario tipo" + "WHERE u.idTipousuario = tipo.idtipousuario";

		try {
			resultado = stmt.executeQuery(query);

			while (resultado.next()) {
				UsuarioVO usuario = new UsuarioVO();
				usuario.setIdusuario(Integer.parseInt(resultado.getString(1)));
				usuario.setTipoUsuarioVO(TipoUsuarioVO.valueOf(resultado.getString(2)));
				usuario.setNome(resultado.getString(3));
				usuario.setCpf(resultado.getString(4));
				usuario.setEmail(resultado.getString(5));
				usuario.setDataCadastro(LocalDate.parse(resultado.getString(6), formaterDate));
				if (resultado.getString(7) != null) {
					usuario.setDataExpiracao(LocalDate.parse(resultado.getString(7), formaterDate));
				}
				usuario.setLogin(resultado.getString(8));
				usuario.setSenha(resultado.getString(9));
				listausuarioVo.add(usuario);
			}

		} catch (SQLException e) {
			System.out.println("erro ao executar query de consultar todos ");
			System.out.println("erro" + getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closePreparedStatement(stmt);
			Banco.closeResultSet(resultado);
		}

		return listausuarioVo;
	}

	// public UsuarioVo consultarUsuarioDAO(UsuarioVo usuarioVO) {

	public UsuarioVO consultarUsuarioDAO(UsuarioVO usuarioVO) {
			Connection conn = Banco.getConnection();
			Statement stmt = Banco.getStatement(conn);
			ResultSet resultado = null;

			UsuarioVO usuario=new UsuarioVO();
			
			String query="SELECT u.idusuario, u.descricao, u.nome, u.cpf, u.email, u.dataCadstro, u.dataExpericao, u.login, u.senha"
					+"FROM usuario u, tipoUsuario tipo"
					+"WHERE u.idTipousuario = tipo.idtipousuario"
					+"AND u.idusuario= "+usuarioVO.getIdusuario();
			
			
			try {
				resultado=stmt.executeQuery(query);
				
				
				if(resultado.next()) {
					
					usuario.setIdusuario(Integer.parseInt(resultado.getString(1)));
					usuario.setTipoUsuarioVO(TipoUsuarioVO.valueOf(resultado.getString(2)));
					usuario.setNome(resultado.getString(3));
					usuario.setCpf(resultado.getString(4));
					usuario.setEmail(resultado.getString(5));
					usuario.setDataCadastro(LocalDate.parse(resultado.getString(6), formaterDate));
					if(resultado.getString(7) != null) {
						usuario.setDataExpiracao(LocalDate.parse(resultado.getString(7),formaterDate));
					}
					usuario.setLogin(resultado.getString(8));
					usuario.setSenha(resultado.getString(9));
				}
			}catch(SQLException e) {
				System.out.println("erro ao executar query de consultar todos ");
			System.out.println("erro"+getMessage());
			}
			finally {
				Banco.closeConnection(conn);
				Banco.closePreparedStatement(stmt);
				Banco.closeResultSet(resultado);
			}
			return usuario;
		}



}

