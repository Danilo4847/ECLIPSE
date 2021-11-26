package view;

import java.util.Scanner;

import modem.vo.TipoUsuarioVO;
import modem.vo.UsuarioVo;

public class Menu {

	Scanner teclado = new Scanner(System.in);

	private static final int OPCAO_MENU_CHAMADAOS=1;
	private static final int OPCAO_MENU_ATENDIMENTOS=2;
	private static final int OPCAO_MENU_RELATORIOS=3;
	private static final int OPCAO_MENU_USUARIOS=4;
	private static final int OPCAO_MENU_SAIR=9;



	public void apresentarmenu(UsuarioVo usuarioVo) {
		int opcao=this.apresentarOpcoesMenu(usuarioVo);
		while(opcao != OPCAO_MENU_SAIR){
			switch(opcao) {
			case OPCAO_MENU_CHAMADAOS:{ 
				MenuChamado menuChamado =new MenuChamado();
				menuChamado.apresentarMenuChamado();
				
				break;
			}
			case OPCAO_MENU_ATENDIMENTOS:{ 
				break;
			}
			case OPCAO_MENU_RELATORIOS:{ 
				break;
			}
			case OPCAO_MENU_USUARIOS:{ 
				if(usuarioVo.getTipoUsuarioVO().equals(TipoUsuarioVO.ADMINISTRADOR)) {
					MenuUsuario menuUsuario = new MenuUsuario();
					menuUsuario.apresentarMenuUsuario();
				}
				break;
			}
			default:{
				System.out.println("\nOpção invalida");
			}

			}
			opcao= this.apresentarOpcoesMenu(usuarioVo);
		}
	}


	private int apresentarOpcoesMenu(UsuarioVo usuarioVo) {
		System.out.println("\n-----Sistema Socorro Desk-----");
		System.out.println("-----Menu principal-----");
		System.out.println("\nOPções");
		System.out.println(OPCAO_MENU_CHAMADAOS+"- chamados");
		if(usuarioVo.getTipoUsuarioVO().equals(TipoUsuarioVO.USUARIO)) {

			System.out.println(OPCAO_MENU_ATENDIMENTOS+"- Atendimentos");
			System.out.println(OPCAO_MENU_RELATORIOS+"- Relatorios");
		}
		if(usuarioVo.getTipoUsuarioVO().equals(TipoUsuarioVO.ADMINISTRADOR)) {
			System.out.println(OPCAO_MENU_USUARIOS+"- Usuários");
		}

		System.out.println(OPCAO_MENU_SAIR+"- Sair");
		System.out.println("\nDigite a Opção");
		return Integer.parseInt(teclado.nextLine());
	}

}
