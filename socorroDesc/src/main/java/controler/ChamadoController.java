package controler;

import mode.bo.ChamadoBO;
import modem.vo.ChamadoVO;

public class ChamadoController {

	public ChamadoVO realizarChamado(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.realizarChamado(chamadoVO);
		
	}
	
	
}
