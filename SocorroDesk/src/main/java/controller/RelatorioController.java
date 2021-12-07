package controller;

import java.util.ArrayList;

import model.bo.RelatorioBO;
import model.dto.RelatorioDTO;


public class RelatorioController {

	public ArrayList<RelatorioDTO> consultarUsuariosDoChamadoFechado() {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.apresentarRelatorioDosUsuariosDoChamado();

		return null;
	}

}
