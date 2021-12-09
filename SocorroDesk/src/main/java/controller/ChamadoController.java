package controller;

import java.util.ArrayList;

import model.bo.ChamadoBO;
import model.vo.ChamadoVO;
import model.vo.UsuarioVO;

public class ChamadoController {




	public ChamadoVO cadastrarChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.cadastrarChamadoBO(chamadoVO);
	}

	public boolean excluirChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.excluirChamadoBO(chamadoVO);
	}

	public boolean atualizarChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.atualizarChamadoBO(chamadoVO);
	}

	public ArrayList<ChamadoVO> consultarTodosChamadosUsuarioController(UsuarioVO usuarioVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.consultarTodosChamadosUsuarioBO(usuarioVO);
	}

	public ArrayList<ChamadoVO> consultarChamadosAbertosUsuarioController(UsuarioVO usuarioVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.consultarChamadosAbertosUsuarioBO(usuarioVO);
	}

	public ArrayList<ChamadoVO> consultarChamadosFechadosController(UsuarioVO usuarioVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.consultarChamadosFechadosBO(usuarioVO);
	}

	public ChamadoVO atenderChamadoController(ChamadoVO chamadoVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.atenderChamadoBO(chamadoVO);
	}

	public ArrayList<ChamadoVO> listarChamadosAbertosController() {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.listarChamadosAbertosBO();
	}

	public ArrayList<ChamadoVO> listarChamadosFechadosTecnicoController(UsuarioVO usuarioVO) {
		ChamadoBO chamadoBO = new ChamadoBO();
		return chamadoBO.listarChamadosFechadosTecnicoBO(usuarioVO);
	}


}