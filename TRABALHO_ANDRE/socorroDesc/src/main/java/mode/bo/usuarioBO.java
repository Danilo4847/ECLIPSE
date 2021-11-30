package mode.bo;


import java.util.ArrayList;

import model.dao.UsuarioDAO;
import modem.vo.TipoUsuarioVO;
import modem.vo.UsuarioVo;

public class usuarioBO {

	public UsuarioVo realizarLoginBO(UsuarioVo usuarioVo) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.realizarLoginDAO(usuarioVo);
	}

	public  ArrayList<TipoUsuarioVO> consultarTiposUsuariosBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTipousuarioDAO();
	}

	public UsuarioVo cadastrarUsuarioBO(UsuarioVo usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarExistenciaRegistroPorCpfDAO(usuarioVO)) {
			System.out.println("\nUsuário já é cadastrado");
		}else {
			usuarioVO=usuarioDAO.cadastrarUsuarioBO(usuarioVO);
		}
		return usuarioVO;
	}

	public boolean escluirUsuarioBO(UsuarioVo usuarioVo) {
		boolean resultado = false;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarExistenciaRegistroPorIdUsuarioDAO(usuarioVo.getIdusuario())) {
			if(usuarioDAO.verificarDesligamentoPorIdUsuarioDAO(usuarioVo)) {
				System.out.println("\nUsuario já foi desligado");
			}else {
				resultado = usuarioDAO.excluirUsuarioDAO(usuarioVo);
			}
		}else {
			System.out.println("usuario não existe na base de dados");
		}

		return resultado;
	}

	public boolean atualizarUsuarioBO(UsuarioVo usuarioVo) {
		boolean resultado = false;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarExistenciaRegistroPorIdUsuarioDAO(usuarioVo.getIdusuario())) {
			if(usuarioDAO.verificarDesligamentoPorIdUsuarioDAO(usuarioVo)) {
				System.out.println("\nUsuario já foi desligado");
			}else {
				resultado = usuarioDAO.atualizarUsuarioDAO(usuarioVo);
			}
		}else {
			System.out.println("usuario não existe na base de dados");
		}

		return resultado;
	}

	public ArrayList<UsuarioVo> consultarTodosUsuariosBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<UsuarioVo> listaUsuarioVo=usuarioDAO.consultarTodosOsUsuariosDAO();
		if(listaUsuarioVo.isEmpty()) {
			System.out.println("lista de usuario esta vazia");
		}
		return listaUsuarioVo;
	}


	
	public UsuarioVo consultarUsuariosBO(UsuarioVo usuarioVO) {
		UsuarioDAO usuarioDAO= new UsuarioDAO();
		UsuarioVo usuario= usuarioDAO.consultarUsuarioDAO(usuarioVO);
		if(usuario==null) {
		System.out.println("\nUsuario não localizado!");
		}
		return usuario;
		}



}
