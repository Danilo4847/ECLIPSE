package model.dao;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



import model.vo.ChamadoVO;
import model.vo.UsuarioVO;


public class ChamadoDAO {

	DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public ChamadoVO cadastrarChamadoBO(ChamadoVO chamadoVO) {
		String query = "INSERT INTO chamados (titulo,descricao,dataabertura) VALUES(?,?,?)";

		Connection conn=Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);



		try {
		
			pstmt.setString(1,chamadoVO.getTitulo());
			pstmt.setString(2,chamadoVO.getDescricao());
			pstmt.setObject(3,chamadoVO.getData());

			
			pstmt.execute();


			ResultSet resultado = pstmt.getGeneratedKeys();

			if(resultado.next()) {
				chamadoVO.setIdchamado(resultado.getInt(1));


			}

		}catch (SQLException e) {
			System.out.println("erro ao executar a query de realizar chamado");
			System.out.println("erro"+getMessage());
		}finally {

			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}


		return chamadoVO;

	}

	private String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean verificarExistenciaPorIdChamadoDAO(int idChamado) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;
        boolean retorno = false;

        String query = "SELECT idchamado FROM chamados WHERE idchamado = "+idChamado;

        try {
            resultado = stmt.executeQuery(query);
            if(resultado.next()) {
                retorno = true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a query que verifica a existÃªncia de chamado por ID.");
            System.out.println("Erro: "+e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closeStatement(stmt);
            Banco.closeConnection(conn);
        }

        return retorno;
    }

    public boolean verificarDonoPorIdUsuarioDAO(ChamadoVO chamadoVO) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;
        boolean retorno = false;

        String query = "SELECT idchamado FROM chamados WHERE idchamado = "+chamadoVO.getIdchamado()+" AND idusuario = "+chamadoVO.getIdusuario();

        try {
            resultado = stmt.executeQuery(query);
            if(resultado.next()) {
                retorno = true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a query que verifica se o chamado pertence ao usuÃ¡rio por ID.");
            System.out.println("Erro: "+e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closeStatement(stmt);
            Banco.closeConnection(conn);
        }

        return retorno;
    }

    public boolean verificarChamadoAbertoDAO(ChamadoVO chamadoVO) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;
        boolean retorno = false;

        String query = "SELECT idchamado FROM chamados WHERE idchamado = "+chamadoVO.getIdchamado()+" AND datafechamento is null";

        try {
            resultado = stmt.executeQuery(query);
            if(resultado.next()) {
                retorno = true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a query que verifica se o chamado estÃ¡ aberto.");
            System.out.println("Erro: "+e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closeStatement(stmt);
            Banco.closeConnection(conn);
        }

        return retorno;
    }

    public boolean excluirChamadoDAO(ChamadoVO chamadoVO) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);

        boolean retorno = false;

        String query = "DELETE FROM chamados WHERE IDCHAMADO = "+chamadoVO.getIdchamado();

        try {
            if(stmt.executeUpdate(query) == 1) {
                retorno = true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a query de exclusÃ£o do chamado.");
            System.out.println("Erro: "+e.getMessage());
        } finally {
            Banco.closeStatement(stmt);
            Banco.closeConnection(conn);
        }

        return retorno;
    }

    public boolean atualizarChamadoDAO(ChamadoVO chamadoVO) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        boolean retorno = false;

        String query = "UPDATE chamados SET " +
                "idusuario = " + chamadoVO.getIdusuario() +
                ", titulo = '" + chamadoVO.getTitulo() +
                "', descricao = '" + chamadoVO.getDescricao() +
                "', dataabertura = '" + chamadoVO.getData() +
                "' WHERE idchamado = "+ chamadoVO.getIdchamado();
                ;

        try {
            if(stmt.executeUpdate(query) == 1) {
                retorno = true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a query de atualizaÃ§Ã£o do chamado.");
            System.out.println("Erro: "+e.getMessage());
        } finally {
            Banco.closeStatement(stmt);
            Banco.closeConnection(conn);
        }

        return retorno;
    }

	public ArrayList<ChamadoVO> consultarTodosChamadosUsuarioDAO(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ArrayList<ChamadoVO> listaChamadosVO = new ArrayList<ChamadoVO>();
		
		String query = "SELECT idchamado, idusuario, idtecnico, titulo, descricao, dataabertura, solucao, datafechamento "
				+"FROM chamados WHERE idusuario = "+chamadoVO.getIdusuario();
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ChamadoVO chamado = new ChamadoVO();
				chamado.setIdchamado(Integer.parseInt(resultado.getString(1)));
				chamado.setIdusuario(Integer.parseInt(resultado.getString(2)));
				if(resultado.getString(3) != null) {
					chamado.setIdtecnico(Integer.parseInt(resultado.getString(3)));
				}else {
					chamado.setIdtecnico(0);
				}
				chamado.setTitulo(resultado.getString(4));
				chamado.setDescricao((resultado.getString(5)));
				chamado.setData(LocalDate.parse(resultado.getString(6), formaterDate));
				if(resultado.getString(7) == null) {
					chamado.setSolucao("NÃ£o resolvido");
				}else {
					chamado.setSolucao(resultado.getString(7));
				}
				if(resultado.getString(8) != null) {
					chamado.setDataFechamaneto(LocalDate.parse(resultado.getString(8), formaterDate));
				}
				listaChamadosVO.add(chamado);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de todos os usuÃ¡rios.");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return listaChamadosVO;
	}

	public ArrayList<ChamadoVO> consultarChamadosAbertosUsuarioDAO(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ArrayList<ChamadoVO> listaChamadosVO = new ArrayList<ChamadoVO>();
		
		String query = "SELECT idchamado, idusuario, idtecnico, titulo, descricao, dataabertura, solucao, datafechamento "
				+"FROM chamados WHERE idusuario = "+chamadoVO.getIdusuario()+" AND datafechamento is null";
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ChamadoVO chamado = new ChamadoVO();
				chamado.setIdchamado(Integer.parseInt(resultado.getString(1)));
				chamado.setIdusuario(Integer.parseInt(resultado.getString(2)));
				if(resultado.getString(3) != null) {
					chamado.setIdtecnico(Integer.parseInt(resultado.getString(3)));
				}else {
					chamado.setIdtecnico(0);
				}
				chamado.setTitulo(resultado.getString(4));
				chamado.setDescricao((resultado.getString(5)));
				chamado.setData(LocalDate.parse(resultado.getString(6), formaterDate));
				if(resultado.getString(7) == null) {
					chamado.setSolucao("NÃ£o resolvido");
				}else {
					chamado.setSolucao(resultado.getString(7));
				}
				if(resultado.getString(8) != null) {
					chamado.setDataFechamaneto(LocalDate.parse(resultado.getString(8), formaterDate));
				}
				listaChamadosVO.add(chamado);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de todos os usuÃ¡rios.");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return listaChamadosVO;
	}

	public ArrayList<ChamadoVO> listarChamadosFechadosDAO(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ArrayList<ChamadoVO> listaChamadosVO = new ArrayList<ChamadoVO>();
		
		String query = "SELECT idchamado, idusuario, idtecnico, titulo, descricao, dataabertura, solucao, datafechamento "
				+"FROM chamados WHERE idusuario = "+chamadoVO.getIdusuario()+" AND datafechamento is not null";
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ChamadoVO chamado = new ChamadoVO();
				chamado.setIdchamado(Integer.parseInt(resultado.getString(1)));
				chamado.setIdusuario(Integer.parseInt(resultado.getString(2)));
				if(resultado.getString(3) != null) {
					chamado.setIdtecnico(Integer.parseInt(resultado.getString(3)));
				}else {
					chamado.setIdtecnico(0);
				}
				chamado.setTitulo(resultado.getString(4));
				chamado.setDescricao(resultado.getString(5));
				chamado.setData(LocalDate.parse(resultado.getString(6), formaterDate));
				if(resultado.getString(7) == null) {
					chamado.setSolucao("NÃ£o resolvido");
				}else {
					chamado.setSolucao(resultado.getString(7));
				}
				if(resultado.getString(8) != null) {
					chamado.setDataFechamaneto(LocalDate.parse(resultado.getString(8), formaterDate));
				}
				listaChamadosVO.add(chamado);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de todos os usuÃ¡rios.");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return listaChamadosVO;
	}

	public ChamadoVO atenderChamadoDAO(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ChamadoVO retorno = new ChamadoVO();
		String query = "UPDATE chamados SET idtecnico = " + chamadoVO.getIdtecnico()
					+ ", solucao = '" + chamadoVO.getSolucao()
					+"', datafechamento = '" + chamadoVO.getData()
					+"' WHERE idChamado = " + chamadoVO.getIdchamado();
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = this.consultarChamadoAtendido(chamadoVO.getIdchamado());
			}
		} catch (SQLException e){
			System.out.println("Erro ao executar a Query de Atendimento do Chamado.");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return retorno;
	}

	private ChamadoVO consultarChamadoAtendido(int idChamado) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ChamadoVO chamadoVO = new ChamadoVO();		
		
		String query = "SELECT idchamado, idusuario, idtecnico, titulo, descricao, dataabertura, solucao, datafechamento "
				+"FROM chamados WHERE idchamado = "+idChamado;
		
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				chamadoVO.setIdchamado(Integer.parseInt(resultado.getString(1)));
				chamadoVO.setIdusuario(Integer.parseInt(resultado.getString(2)));
				chamadoVO.setIdtecnico(Integer.parseInt(resultado.getString(3)));
				chamadoVO.setTitulo(resultado.getString(4));
				chamadoVO.setDescricao(resultado.getString(5));
				chamadoVO.setData(LocalDate.parse(resultado.getString(6), formaterDate));
				chamadoVO.setSolucao(resultado.getString(7));
				chamadoVO.setDataFechamaneto(LocalDate.parse(resultado.getString(8), formaterDate));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Consulta de chamado por ID");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return chamadoVO;
	}

	public ArrayList<ChamadoVO> listarChamadosAbertosDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ArrayList<ChamadoVO> listaChamadosVO = new ArrayList<ChamadoVO>();
		
		String query = "SELECT idchamado, idusuario, idtecnico, titulo, descricao, dataabertura, solucao, datafechamento "
				+"FROM chamados WHERE datafechamento is null";
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ChamadoVO chamado = new ChamadoVO();
				chamado.setIdchamado(Integer.parseInt(resultado.getString(1)));
				chamado.setIdusuario(Integer.parseInt(resultado.getString(2)));
				if(resultado.getString(3) != null) {
					chamado.setIdtecnico(Integer.parseInt(resultado.getString(3)));
				}else {
					chamado.setIdtecnico(0);
				}
				chamado.setTitulo(resultado.getString(4));
				chamado.setDescricao((resultado.getString(5)));
				chamado.setData(LocalDate.parse(resultado.getString(6), formaterDate));
				if(resultado.getString(7) == null) {
					chamado.setSolucao("NÃ£o resolvido");
				}else {
					chamado.setSolucao(resultado.getString(7));
				}
				if(resultado.getString(8) != null) {
					chamado.setDataFechamaneto(LocalDate.parse(resultado.getString(8), formaterDate));
				}
				listaChamadosVO.add(chamado);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de todos os usuÃ¡rios.");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return listaChamadosVO;
	}

	public ArrayList<ChamadoVO> listarChamadosFechadosTecnicoDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		ArrayList<ChamadoVO> listaChamadosVO = new ArrayList<ChamadoVO>();
		
		String query = "SELECT idchamado, idusuario, idtecnico, titulo, descricao, dataabertura, solucao, datafechamento "
				+"FROM chamados WHERE idtecnico = "+usuarioVO.getIdusuario();
		
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ChamadoVO chamado = new ChamadoVO();
				chamado.setIdchamado(Integer.parseInt(resultado.getString(1)));
				chamado.setIdusuario(Integer.parseInt(resultado.getString(2)));
				if(resultado.getString(3) != null) {
					chamado.setIdtecnico(Integer.parseInt(resultado.getString(3)));
				}else {
					chamado.setIdtecnico(0);
				}
				chamado.setTitulo(resultado.getString(4));
				chamado.setDescricao(resultado.getString(5));
				chamado.setData(LocalDate.parse(resultado.getString(6), formaterDate));
				if(resultado.getString(7) == null) {
					chamado.setSolucao("NÃ£o resolvido");
				}else {
					chamado.setSolucao(resultado.getString(7));
				}
				if(resultado.getString(8) != null) {
					chamado.setDataFechamaneto(LocalDate.parse(resultado.getString(8), formaterDate));
				}
				listaChamadosVO.add(chamado);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de todos os usuÃ¡rios.");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return listaChamadosVO;
	}


	
	
	
}