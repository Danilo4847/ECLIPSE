package controller;

import model.bo.ChamadoBO;
import model.vo.ChamadoVO;

public class ChamadoController {



	public ChamadoVO cadastrarChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.cadastrarChamadoBO(chamadoVO);
	}
	
	
}
