package controller;

import java.util.ArrayList;

import model.bo.UsuarioBO;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class UsuarioController {

	public UsuarioVO realizarLoginController(UsuarioVO usuarioVO) {

		UsuarioBO UsuarioBO = new UsuarioBO();
		return UsuarioBO.realizarLoginBO(usuarioVO);
	}

	public UsuarioVO cadastrarUsuarioController(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBo= new UsuarioBO();
		return usuarioBo.cadastrarUsuarioBO(usuarioVO);
	}

	public ArrayList<TipoUsuarioVO> consultarTipoUsuarioController() {
		UsuarioBO usuarioBo = new UsuarioBO();
		return usuarioBo.consultarTiposUsuariosBO();

	}

	public boolean excluirUsuarioController(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBo = new UsuarioBO();
		return usuarioBo.escluirUsuarioBO(usuarioVO);
	}

	public boolean atualizarUsuarioController(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBo = new UsuarioBO();
		return usuarioBo.atualizarUsuarioBO(usuarioVO);
	}

	public ArrayList<UsuarioVO> consultarTodosUsuariosController() {
	UsuarioBO usuarioBo = new UsuarioBO();
	
		return usuarioBo.consultarTodosUsuariosBO();
	}

	public UsuarioVO consultarUsuarioController(UsuarioVO usuarioVO) {
		UsuarioBO usuarioBo = new UsuarioBO();
		
		return usuarioBo.consultarUsuariosBO(usuarioVO);
	}
	


}
