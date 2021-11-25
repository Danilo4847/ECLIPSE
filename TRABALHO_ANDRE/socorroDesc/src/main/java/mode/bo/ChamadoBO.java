package mode.bo;

import model.dao.ChamadoDAO;

import modem.vo.ChamadoVO;


public class ChamadoBO {

	public ChamadoVO realizarChamado(ChamadoVO chamadoVO) {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		if(chamadoDAO.verificarExistenciaRegistroPorIpDAO(chamadoVO)) {
			System.out.println("\nUsuário já é cadastrado");
		}else {
			chamadoVO=chamadoDAO.cadastrarChamadoBO(chamadoVO);
		}
		return chamadoVO;
		
		
	
		
		}

	}


