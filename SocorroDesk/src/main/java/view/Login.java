package view;

import java.util.Scanner;

import controller.UsuarioController;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class Login {

	private static final int OPCAO_MENU_LOGIN=1;
	private static final int OPCAO_MENU_CADASTRO = 2;
	private static final int OPCAO_MENU_SAIR=3;

	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuLogin() {


		int opcao=this.apresentarOPcoesMenu();
		while(opcao!=OPCAO_MENU_SAIR) {
			switch(opcao) {
			case OPCAO_MENU_LOGIN:{
				UsuarioVO usuarioVO=this.realizarLogin();
				if(usuarioVO.getIdusuario()!=0 && usuarioVO.getDataExpiracao()==null) {

					System.out.println("usuario logado"+usuarioVO.getLogin());
					System.out.println("perfil"+usuarioVO.getTipoUsuarioVO());

					Menu menu=new Menu();
					menu.apresentarMenu(usuarioVO);
				}

				break;
			}
			case OPCAO_MENU_CADASTRO:{
				this.cadastrarNovoUsuario();
				break;
			}
			default:{
				System.out.println("opção invalida");
				break;
			}
			}
			opcao=this.apresentarOPcoesMenu();
		}
	}





	private int apresentarOPcoesMenu() {
		System.out.println("\n----Sistema Socorro Desk ---");
		System.out.println("\nOpção:");
		System.out.println(OPCAO_MENU_LOGIN+" - Entrar");
		System.out.println(OPCAO_MENU_CADASTRO+" - Criar conta");
		System.out.println(OPCAO_MENU_SAIR+" - sair");
		System.out.println("\nDigite uma opção; ");
		return Integer.parseInt(teclado.nextLine());
	}

	private UsuarioVO realizarLogin() {
		UsuarioVO usuarioVO=new UsuarioVO();
		System.out.println("\n----informações---\n");
		System.out.print("login");
		usuarioVO.setLogin(teclado.nextLine());
		System.out.println("senha");
		usuarioVO.setSenha(teclado.nextLine());


		if(usuarioVO.getLogin().isEmpty() ||  usuarioVO.getSenha().isEmpty()) {
			System.out.println("os campos login e senha são obrigatorio");

		}else {
			UsuarioController usuarioController = new UsuarioController();
			usuarioVO=usuarioController.realizarLoginController(usuarioVO);

			if(usuarioVO.getNome()==null || usuarioVO.getNome().isEmpty()) {
				System.out.println("\nUsuário não encontrado");
			}
			if(usuarioVO.getDataExpiracao() != null) {
				System.out.println("\nUsuário expirado");
			}
		}
		return usuarioVO;
	}
	// terminar o método de cadastra usuario
	private void cadastrarNovoUsuario() {
		UsuarioVO usuarioVO = new UsuarioVO();

		usuarioVO.setTipoUsuarioVO(TipoUsuarioVO.USUARIO);

		MenuUsuario menuUsuario = new MenuUsuario();

		menuUsuario.cadastrarUsuario(usuarioVO);

	}


}
