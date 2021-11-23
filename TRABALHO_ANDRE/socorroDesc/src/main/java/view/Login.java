package view;

import java.util.Scanner;

import controler.UsuarioController;
import modem.vo.TipoUsuarioVO;
import modem.vo.UsuarioVo;

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
			UsuarioVo usuarioVo=this.realizarLogin();
			if(usuarioVo.getIdusuario()!=0 && usuarioVo.getDataExpiracao()==null) {
				
			System.out.println("usuario logado"+usuarioVo.getLogin());
			System.out.println("perfil"+usuarioVo.getTipoUsuarioVO());
				
				
				
			Menu menu=new Menu();
			menu.apresentarmenu(usuarioVo);
				
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
	
	private UsuarioVo realizarLogin() {
	UsuarioVo usuarioVo=new UsuarioVo();
	System.out.println("\n----informações---\n");
	System.out.print("login");
	usuarioVo.setLogin(teclado.nextLine());
	System.out.println("senha");
	usuarioVo.setSenha(teclado.nextLine());
	
	
	if(usuarioVo.getLogin().isEmpty() ||  usuarioVo.getSenha().isEmpty()) {
		System.out.println("os campos login e senha são obrigatorio");
		
	}else {
		UsuarioController usuarioController = new UsuarioController();
		usuarioVo=usuarioController.realizarLoginController(usuarioVo);
		
		if(usuarioVo.getNome()==null || usuarioVo.getNome().isEmpty()) {
			System.out.println("\nUsuário não encontrado");
		}
		if(usuarioVo.getDataExpiracao() != null) {
			System.out.println("\nUsuário expirado");
		}
	}
	return usuarioVo;
	}
 // terminar o método de cadastra usuario
	private void cadastrarNovoUsuario() {
		UsuarioVo usuarioVO = new UsuarioVo();

        usuarioVO.setTipoUsuarioVO(TipoUsuarioVO.USUARIO);

        MenuUsuario menuUsuario = new MenuUsuario();

        menuUsuario.cadastrarUsuario(usuarioVO);
		
	}


}
