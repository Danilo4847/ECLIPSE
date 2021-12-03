package controller;

import java.util.ArrayList;

import model.bo.ChamadoBO;
import model.vo.ChamadoVO;

public class ChamadoController {



	public ChamadoVO cadastrarChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.cadastrarChamadoBO(chamadoVO);
	}

	public ArrayList<ChamadoVO> listarChamadosAbertosController() {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.listarChamadosAbertos();
	}

	public ArrayList<ChamadoVO> listarChamadosFechadosController() {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.listarChamadosFechados();
	}
	
	public ChamadoVO atenderChamadoController(ChamadoVO chamdoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.atenderChamadoBO();
	}
	
	
}
