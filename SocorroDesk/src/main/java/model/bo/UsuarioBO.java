package model.bo;


import java.util.ArrayList;

import model.dao.UsuarioDAO;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class UsuarioBO {

	public UsuarioVO realizarLoginBO(UsuarioVO usuarioVO) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.realizarLoginDAO(usuarioVO);
	}

	public  ArrayList<TipoUsuarioVO> consultarTiposUsuariosBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTipousuarioDAO();
	}

	public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarExistenciaRegistroPorCpfDAO(usuarioVO)) {
			System.out.println("\nUsuário já é cadastrado");
		}else {
			usuarioVO=usuarioDAO.cadastrarUsuarioBO(usuarioVO);
		}
		return usuarioVO;
	}

	public boolean escluirUsuarioBO(UsuarioVO usuarioVO) {
		boolean resultado = false;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarExistenciaRegistroPorIdUsuarioDAO(usuarioVO.getIdusuario())) {
			if(usuarioDAO.verificarDesligamentoPorIdUsuarioDAO(usuarioVO)) {
				System.out.println("\nUsuario já foi desligado");
			}else {
				resultado = usuarioDAO.excluirUsuarioDAO(usuarioVO);
			}
		}else {
			System.out.println("usuario não existe na base de dados");
		}

		return resultado;
	}

	public boolean atualizarUsuarioBO(UsuarioVO usuarioVO) {
		boolean resultado = false;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarExistenciaRegistroPorIdUsuarioDAO(usuarioVO.getIdusuario())) {
			if(usuarioDAO.verificarDesligamentoPorIdUsuarioDAO(usuarioVO)) {
				System.out.println("\nUsuario já foi desligado");
			}else {
				resultado = usuarioDAO.atualizarUsuarioDAO(usuarioVO);
			}
		}else {
			System.out.println("usuario não existe na base de dados");
		}

		return resultado;
	}

	public ArrayList<UsuarioVO> consultarTodosUsuariosBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<UsuarioVO> listaUsuarioVo=usuarioDAO.consultarTodosOsUsuariosDAO();
		if(listaUsuarioVo.isEmpty()) {
			System.out.println("lista de usuario esta vazia");
		}
		return listaUsuarioVo;
	}


	
	public UsuarioVO consultarUsuariosBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO= new UsuarioDAO();
		UsuarioVO usuario= usuarioDAO.consultarUsuarioDAO(usuarioVO);
		if(usuario==null) {
		System.out.println("\nUsuario não localizado!");
		}
		return usuario;
		}



}
