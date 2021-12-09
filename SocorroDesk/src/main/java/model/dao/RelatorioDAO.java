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

		String query="SELECT idchamdo,usuario.nome,chmado.idtecnico FROM usuario INNER JOIN on chamado.idchamado=usuario.idchamado";

		try {
			resultado=stmt.executeQuery(query);

			if(resultado.next()){
				ChamadoVO chamadoVO= new ChamadoVO();
				UsuarioVO usuarioVO= new UsuarioVO();
				chamadoVO.setIdchamado(Integer.parseInt(resultado.getString(1)));
				usuarioVO.setNome(resultado.getString(2));
				chamadoVO.setIdtecnico(Integer.parseInt(resultado.getString(3)));


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