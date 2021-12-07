package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.RelatorioDTO;

public class RelatorioDAO {

	public ArrayList<RelatorioDTO> consultarTodosChamadosUsuarioDAO() {

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<RelatorioDTO> relatorioDeChamadosFechados= new ArrayList<RelatorioDTO>();

		String query="SELECT idchamdo,usuario.nome,chamado,chmado.idtecnico FROM usuario INNER JOIN on chamado.idchamado=usuario.idchamado";









		return null;
	}


}
