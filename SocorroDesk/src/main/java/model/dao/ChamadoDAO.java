package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Statement;

import model.vo.ChamadoVO;


public class ChamadoDAO {

	
	public ChamadoVO cadastrarChamadoBO(ChamadoVO chamadoVO) {
		String query = "INSERT INTO chamados (titulo,descricao,dataabertura) VALUES(?,?,?)";

		Connection conn=Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);



		try {
		
			pstmt.setString(1,chamadoVO.getTitulo());
			pstmt.setString(2,chamadoVO.getDescriao());
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

	public boolean verificarExistenciaRegistroPorIpDAO(ChamadoVO chamadoVO) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<ChamadoVO> listarChamadosAbertosDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ChamadoVO> listarChamadosFechadosDAO() {
			Connection conn= Banco.getConnection();
			Statement stmt = Banco.getStatement(conn);
			ResultSet resultado = null;
			ChamadoVO chamadoVO = new ChamadoVO();
			String query = "SELECT idChamado, idUsuario, idTecnico, titulo, descricao, dataAbertura,"
					+"solucao, dataFechament"
					+"FROM chamados"
					+"WHERE idChamado= "+usuarioVO.getTitulo()+" "
					+"AND dataFechamento is not null";
			try {
				resultado = stmt.executeQuery(query);
				while(resultado.next()) {
					chamadoVO.setIdchamado(Integer.parseInt(resultado.getString(1)));
					chamadoVO.setIdusuario(Integer.parseInt(resultado.getString(2)));
					chamadoVO.setIdtecnico(Integer.parseInt(resultado.getString(3)));
					chamadoVO.setTitulo(resultado.getString(4));
					chamadoVO.setDescricao(resultado.getString(5));
					chamadoVO.setDataAbertura(LocalDate.parse(resultado.getString(6),formateDate));
					chamadoVO.setSolucao(resultado.getString(7));
					chamadoVO.setDataFechamaneto(LocalDate.parse(resultado.getString(8),formateDate));
				
				}
			} catch(SQLException e) {
				System.out.println("erro ao executa query no atendimento");
				System.out.println("erro"+e.getMessage());
			}finally {
				Banco.closeConnection(conn);
				Banco.closeStatement(stmt);
			}
			
			return chamadoVO;
		}
	
	
	public ChamadoVO atenderChamadoDAO(ChamadoVO chamadoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ChamadoVO retorno = new ChamadoVO();
		String quary = "UPDATE chamado SET idTecnico ="+chamadoVO.getIdtecnico+",solucao='"+chamadoVO.getSolucao()+"',dataFechamaento='"+chamadoVO.getDataFechamaneto()
		+"'WHERE idchamado="+chamadoVO.getIdchamado();
		
		try {
			if(stmt.executeUpdate(query)==1) {
				retorno = this.consultarChamadosAbertos(ChamadoVO.getIdchamado())
			}
		} catch(SQLException e) {
			System.out.println("erro ao executa query no atendimento");
			System.out.println("erro"+e.getMessage());
		}finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
		}
		
		return retorno;
	}
	
	private ChamadoVO consultarChamadosAtendidos(int idChamado) {
		Connection conn= Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ChamadoVO chamadoVO = new ChamadoVO();
		String query = "SELECT idChamado, idUsuario, idTecnico, titulo, descricao, dataAbertura,"
				+"solucao, dataFechament"
				+"FROM chamados"
				+"WHERE idChamado= "+idChamado;
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				chamadoVO.setIdchamado(Integer.parseInt(resultado.getString(1)));
				chamadoVO.setIdusuario(Integer.parseInt(resultado.getString(2)));
				chamadoVO.setIdtecnico(Integer.parseInt(resultado.getString(3)));
				chamadoVO.setTitulo(resultado.getString(4));
				chamadoVO.setDescricao(resultado.getString(5));
				chamadoVO.setDataAbertura(LocalDate.parse(resultado.getString(6),formateDate));
				chamadoVO.setSolucao(resultado.getString(7));
				chamadoVO.setDataFechamaneto(LocalDate.parse(resultado.getString(8),formateDate));
			
			}
		} catch(SQLException e) {
			System.out.println("erro ao executa query no atendimento");
			System.out.println("erro"+e.getMessage());
		}finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
		}
		
		return chamadoVO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}