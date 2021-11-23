package controler;

import java.util.ArrayList;

import mode.bo.usuarioBO;
import modem.vo.TipoUsuarioVO;
import modem.vo.UsuarioVo;

public class UsuarioController {

	public UsuarioVo realizarLoginController(UsuarioVo usuarioVo) {

		usuarioBO usuarioBO = new usuarioBO();
		return usuarioBO.realizarLoginBO(usuarioVo);
	}

	public UsuarioVo cadastrarUsuarioController(UsuarioVo usuarioVo) {
		usuarioBO usuarioBo= new usuarioBO();
		return usuarioBo.cadastrarUsuarioBO(usuarioVo);
	}

	public ArrayList<TipoUsuarioVO> consultarTipoUsuarioController() {
		usuarioBO usuarioBo = new usuarioBO();
		return usuarioBo.consultarTiposUsuariosBO();

	}

	public boolean excluirUsuarioController(UsuarioVo usuarioVo) {
		usuarioBO usuarioBo = new usuarioBO();
		return usuarioBo.escluirUsuarioBO(usuarioVo);
	}

	public boolean atualizarUsuarioController(UsuarioVo usuarioVo) {
		usuarioBO usuarioBo = new usuarioBO();
		return usuarioBo.atualizarUsuarioBO(usuarioVo);
	}

}
