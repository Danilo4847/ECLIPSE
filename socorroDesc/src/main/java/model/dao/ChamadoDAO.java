package model.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modem.vo.ChamadoVO;


public class ChamadoDAO {

	
	public ChamadoVO cadastrarChamadoBO(ChamadoVO chamadoVO) {
		String query = "INSERT INTO usuario (titulo, descricao, dataabertura, solucao, datafechamento) VALUES(?,?,?,?,?)";

		Connection conn=Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);



		try {

			pstmt.setString(1,chamadoVO.getTitulo());
			pstmt.setString(2,chamadoVO.getDescriao());
			pstmt.setObject(4,chamadoVO.getData());
			pstmt.setString(3,chamadoVO.getSolucao());
			pstmt.setObject(5,chamadoVO.getDataFechamaneto());
			pstmt.execute();


			ResultSet resultado = pstmt.getGeneratedKeys();

			if(resultado.next()) {
				chamadoVO.setIdchamado(resultado.getInt(1));


			}

		}catch (SQLException e) {
			System.out.println("erro ao executar a query de criar chamado");
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

	public ChamadoVO cadastrarUsuarioBO(ChamadoVO chamadoVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}