package model.bo;

import java.util.ArrayList;

import model.dao.ChamadoDAO;

import model.vo.ChamadoVO;



public class ChamadoBO {

	

	public ChamadoVO cadastrarChamadoBO(ChamadoVO chamadoVO) {
			ChamadoDAO chamadoDAO = new ChamadoDAO();
			if(chamadoDAO.verificarExistenciaRegistroPorIpDAO(chamadoVO)) {
				System.out.println("\nUsuário já é cadastrado");
			}else {
				chamadoVO=chamadoDAO.cadastrarChamadoBO(chamadoVO);
			}
			return chamadoVO;
		}

	public ArrayList<ChamadoVO> listarChamadosAbertos() {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> chamadosVO= chamadoDAO.listarChamadosAbertosDAO();
		if(chamadosVO.isEmpty()) {
			System.out.println("lista está vazia");
		}
		return chamadosVO;	
	}

	public ArrayList<ChamadoVO> listarChamadosFechados() {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> chamadosVO= chamadoDAO.listarChamadosFechadosDAO();
		if(chamadosVO.isEmpty()) {
			System.out.println("lista está vazia");
		}
		return chamadosVO;	
	}

	public ChamadoVO atenderChamadoBO() {
		ChamadoVO retorno= new ChamadoVO();
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		if(chamadoDAO.verificarExistenciaRegistroPorIdchamado(chamadoVO.getIdchamado())) {
			System.out.println("chamado ja se encontra fechado na bas e de dados");
		}else {
			retorno = chamadoDAO.atenderChamadoDAO(chamadoVO);
		}
		}else{
		System.out.println("chamado não existe na base de dados");
	}
	return retorno;
	}
}
	


