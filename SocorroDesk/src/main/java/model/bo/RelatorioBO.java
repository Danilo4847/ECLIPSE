package model.bo;

import java.util.ArrayList;

import model.dao.RelatorioDAO;
import model.dto.RelatorioDTO;


public class RelatorioBO {

	public ArrayList<RelatorioDTO> apresentarRelatorioDosUsuariosDoChamado() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		ArrayList<RelatorioDTO> relatorioUsuariosDeChamadoFechado = relatorioDAO.consultarTodosChamadosUsuarioDAO();
		
		
		return relatorioUsuariosDeChamadoFechado;
		
		

	}

	
	
	
}
