package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.ChamadoVO;


public class ChamadoDAO {

	
	public ChamadoVO cadastrarChamadoBO(ChamadoVO chamadoVO) {
		String query = "INSERT INTO usuario (idchamado, idusuario, idtecnico, titulo, descricao, dataabertura, solucao, datafechamento) VALUES(?,?,?,?,?,?,?,?)";

		Connection conn=Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);



		try {
			pstmt.setInt(1, chamadoVO.getIdusuario().getIdusuario());
			pstmt.setString(2,chamadoVO.getTitulo());
			pstmt.setString(3,chamadoVO.getDescriao());
			pstmt.setString(4,chamadoVO.getSolucao());
			pstmt.setObject(5,chamadoVO.getData());
			pstmt.setObject(5,chamadoVO.getDataFechamaneto());
			pstmt.setObject(6,chamadoVO.getIdchamado());
			pstmt.setInt(7,chamadoVO.getIdchamado());
			pstmt.execute();


			ResultSet resultado = pstmt.getGeneratedKeys();

			if(resultado.next()) {
				chamadoVO.setIdchamado(resultado.getInt(1));


			}

		}catch (SQLException e) {
			System.out.println("erro ao executar a query de consulta dos tipos de usuário");
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
