package controller;

import model.bo.ChamadoBO;
import model.vo.ChamadoVO;

public class ChamadoController {

	public ChamadoVO realizarChamado(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.realizarChamado(chamadoVO);
		
	}
	
	
}
