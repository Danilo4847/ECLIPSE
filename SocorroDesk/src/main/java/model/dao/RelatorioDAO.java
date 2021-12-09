package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.RelatorioDTO;
import model.vo.ChamadoVO;
import model.vo.UsuarioVO;

public class RelatorioDAO {

	public ArrayList<RelatorioDTO> consultarTodosChamadosUsuarioDAO() {

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<RelatorioDTO> relatorioDeChamadosFechados= new ArrayList<RelatorioDTO>();

		String query="SELECT c.idchamado, dono.nome, tecnico.nome "
				+ " FROM usuario dono, usuario tecnico, chamados c "
				+ " where c.idusuario = dono.idusuario "
				+ " and c.idtecnico = tecnico.idusuario ";

		try {
			resultado=stmt.executeQuery(query);

			if(resultado.next()){
				RelatorioDTO item = new RelatorioDTO();
				item.setIdChamado(resultado.getInt(1));
				item.setNomeCriador(resultado.getString(2));
				item.setnomeTecnico(resultado.getString(3));
				relatorioDeChamadosFechados.add(item);

			}

		} catch (SQLException e) {
			System.out.println("erro ao executar query de gerar relatorio de chamados fechados ");
			System.out.println("erro" + getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closePreparedStatement(stmt);
			Banco.closeResultSet(resultado);
		}

		return relatorioDeChamadosFechados;


	}

	private String getMessage() {
		return null;
	}


}