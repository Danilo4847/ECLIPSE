package model.bo;

import java.util.ArrayList;

import model.dao.ChamadoDAO;

import model.vo.ChamadoVO;
import model.vo.UsuarioVO;



public class ChamadoBO {

	


	public ChamadoVO cadastrarChamadoBO(ChamadoVO chamadoVO) {
			ChamadoDAO chamadoDAO = new ChamadoDAO();
			if(chamadoDAO.verificarExistenciaPorIdChamadoDAO(chamadoVO.getIdchamado())) {
				System.out.println("\nUsuário já é cadastrado");
			}else {
				chamadoVO = chamadoDAO.cadastrarChamadoBO(chamadoVO);
			}
			return chamadoVO;
		}
	
	public boolean excluirChamadoBO(ChamadoVO chamadoVO) {
        boolean resultado = false;
        ChamadoDAO chamadoDAO = new ChamadoDAO();
        if(chamadoDAO.verificarExistenciaPorIdChamadoDAO(chamadoVO.getIdchamado())){
            if(chamadoDAO.verificarDonoPorIdUsuarioDAO(chamadoVO)){
                if(chamadoDAO.verificarChamadoAbertoDAO(chamadoVO)){
                    resultado = chamadoDAO.excluirChamadoDAO(chamadoVO);
                }else{
                    System.out.println("\nO chamado já está fechado!");
                }
            }else{
                System.out.println("\nVocê não possui autoria do chamado!");
            }
        }else {
            System.out.println("\nChamado não existe!");
        }

        return resultado;
    }


    public boolean atualizarChamadoBO(ChamadoVO chamadoVO) {
        ChamadoDAO chamadoDAO = new ChamadoDAO();
        boolean retorno = false;
        if(chamadoDAO.verificarExistenciaPorIdChamadoDAO(chamadoVO.getIdchamado())){
            if(chamadoDAO.verificarDonoPorIdUsuarioDAO(chamadoVO)){
                if(chamadoDAO.verificarChamadoAbertoDAO(chamadoVO)){
                    retorno = chamadoDAO.atualizarChamadoDAO(chamadoVO);
                }else{
                    System.out.println("\nO chamado já está fechado!");
                }
            }else{
                System.out.println("\nVocê não possui autoria do chamado!");
            }
        }else {
            System.out.println("\nChamado não existe!");
        }

        return retorno;
    }

	public ArrayList<ChamadoVO> consultarTodosChamadosUsuarioBO(UsuarioVO usuarioVO) {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> listaChamadosVO = chamadoDAO.consultarTodosChamadosUsuarioDAO(usuarioVO);
		if(listaChamadosVO.isEmpty()) {
			System.out.println("\nLista de chamados está vazia.");
		}
		return listaChamadosVO;
	}

	public ArrayList<ChamadoVO> consultarChamadosAbertosUsuarioBO(UsuarioVO usuarioVO) {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> listaChamadosAbertosVO = chamadoDAO.consultarChamadosAbertosUsuarioDAO(usuarioVO);
		if(listaChamadosAbertosVO.isEmpty()) {
			System.out.println("\nLista de chamados abertos está vazia.");
		}
		return listaChamadosAbertosVO;
	}

	public ArrayList<ChamadoVO> consultarChamadosFechadosBO(UsuarioVO usuarioVO) {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> listaChamadosFechadosVO = chamadoDAO.listarChamadosFechadosDAO(usuarioVO);
		if(listaChamadosFechadosVO.isEmpty()) {
			System.out.println("\nLista de chamados fechados está vazia.");
		}
		return listaChamadosFechadosVO;
	}

	public ChamadoVO atenderChamadoBO(ChamadoVO chamadoVO) {
		ChamadoVO chamado = new ChamadoVO();
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		if(chamadoDAO.verificarExistenciaPorIdChamadoDAO(chamadoVO.getIdchamado())){
			if(chamadoDAO.verificarChamadoAbertoDAO(chamadoVO)){
				chamado = chamadoDAO.atenderChamadoDAO(chamadoVO);
			}else {
				System.out.println("\nChamado está fechado!");
			}
		}else {
			System.out.println("\nChamado não existe!");
		}
		return chamado;
	}

	public ArrayList<ChamadoVO> listarChamadosAbertosBO() {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> chamadosVO = chamadoDAO.listarChamadosAbertosDAO();
		if(chamadosVO.isEmpty()) {
			System.out.println("\nLista de Chamados está vazia.");
		}
		return chamadosVO;
	}

	public ArrayList<ChamadoVO> listarChamadosFechadosTecnicoBO(UsuarioVO usuarioVO) {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<ChamadoVO> chamadosVO = chamadoDAO.listarChamadosFechadosTecnicoDAO(usuarioVO);
		if(chamadosVO.isEmpty()) {
			System.out.println("\nLista de Chamados está vazia.");
		}
		return chamadosVO;
	}

}
	
