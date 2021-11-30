package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controler.UsuarioController;
import modem.vo.TipoUsuarioVO;
import modem.vo.UsuarioVo;

public class MenuUsuario {

	Scanner teclado= new Scanner(System.in);
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private static final int OPCAO_MENU_CADASTRAR_USUAIO=1;
	private static final int OPCAO_MENU_CONSULTARD_USUARIO=2;
	private static final int OPCAO_MENU_ATUALIZAR_USUARIO=3;
	private static final int OPCAO_MENU_EXCLUIR_USUARIO=4;
	private static final int OPCAO_MENU_USUAIO_SAIR=5;

	private static final int OPCAO_MENU_CONSULTAR_TODOS_USUARIOS=1;
	private static final int OPCAO_MENU_CONSULTAR_UM_USUARIO=2;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO_SAIR=9;

	public void apresentarMenuUsuario() {
		int opcao=this.apresentarOpcaoMenu();


		while(opcao!=OPCAO_MENU_USUAIO_SAIR) {


			switch(opcao) {
			case OPCAO_MENU_CADASTRAR_USUAIO:{
				UsuarioVo usuarioVo= new UsuarioVo();
				this.cadastrarUsuario(usuarioVo);
				break;
			}
			case OPCAO_MENU_CONSULTARD_USUARIO:{
				this.consultarUsuario();
				break;
			}
			case OPCAO_MENU_ATUALIZAR_USUARIO:{
				this.atualizarUsuario();
				break;
			}
			case OPCAO_MENU_EXCLUIR_USUARIO:{
				this.excluirUsuario();
				break;

			}
			default:{
				System.out.println("\nOPção invalida");
				break;
			}
			}
			opcao=this.apresentarOpcaoMenu();
		}
	}




	private int apresentarOpcaoMenu() {
		System.out.println("\n---Sistema Socorro Desk---");
		System.out.println("\n---Menu Cadastro usuario---");
		System.out.println(OPCAO_MENU_CADASTRAR_USUAIO+"-Cadastrar usuario-");
		System.out.println(OPCAO_MENU_CONSULTARD_USUARIO+"-Consultar usuario-");
		System.out.println(OPCAO_MENU_ATUALIZAR_USUARIO+"-Atualizar usuario-");
		System.out.println(OPCAO_MENU_EXCLUIR_USUARIO+"-Excluir usuario-");
		System.out.println(OPCAO_MENU_USUAIO_SAIR+"-sair-");
		
		
		
		System.out.print("\nDigite a opção");
		return Integer.parseInt(teclado.nextLine());

	}



	public void cadastrarUsuario(UsuarioVo usuarioVo) {
		if(usuarioVo.getTipoUsuarioVO()==null) {
			do {
				usuarioVo.setTipoUsuarioVO(TipoUsuarioVO.getTipoUsuarioVoPorValor(this.apresentarOperacoesTipoUsuario()));
			}while(usuarioVo.getTipoUsuarioVO()==null);
		}
		System.out.println("\nDigite o nome do usuario");
		usuarioVo.setNome(teclado.nextLine());
		System.out.println("Digite o cpf do usuario");
		usuarioVo.setCpf(teclado.nextLine());
		System.out.println("\nDigite o email do usuario");
		usuarioVo.setEmail(teclado.nextLine());
		usuarioVo.setDataCadastro(LocalDate.now());
		System.out.println("Digite o login");
		usuarioVo.setLogin(teclado.nextLine());
		System.out.println("digite a senha");
		usuarioVo.setSenha(teclado.nextLine());

		if(this.validarCamposCadastro(usuarioVo)) {
			UsuarioController usuarioController = new UsuarioController();
			usuarioVo=usuarioController.cadastrarUsuarioController(usuarioVo);

			if(usuarioVo.getIdusuario()!=0) {
				System.out.println("usuario cadastrado com sucesso");
			}else {
				System.out.println("não foi possivel cadastrar usuario");
			}

		}

	}

	private int apresentarOperacoesTipoUsuario() {
		UsuarioController usuarioController= new UsuarioController();
		ArrayList<TipoUsuarioVO> tipoUsuarioVO= usuarioController.consultarTipoUsuarioController();
		System.out.println("---tipos de usuario---");
		System.out.println("Opções:");
		for(int i = 0;i< tipoUsuarioVO.size();i++) {
			System.out.println(tipoUsuarioVO.get(i).getValor()+"-"+tipoUsuarioVO.get(i));
		}
		System.out.println("digite uma opção");
		return Integer.parseInt(teclado.nextLine());
	}




	private boolean validarCamposCadastro(UsuarioVo usuarioVo) {
		boolean resultado=true;

		System.out.println();
		if(usuarioVo.getNome().isEmpty()||usuarioVo.getNome()==null) {
			System.out.println("O campo nome é obrigatorio");
			resultado = false;

		}
		if(usuarioVo.getCpf().isEmpty()||usuarioVo.getCpf()==null) {
			System.out.println("O campo cpf é obrigatorio");
			resultado = false;
			return resultado;
		}
		if(usuarioVo.getEmail().isEmpty()||usuarioVo.getEmail()==null) {
			System.out.println("O campo Emaile é obrigatorio");
			resultado = false;
		}
		if(usuarioVo.getDataCadastro()==null) {
			System.out.println("O campo data cadastro é obrigatorio");
			resultado = false;
		}
		if(usuarioVo.getLogin().isEmpty()||usuarioVo.getLogin()==null) {
			System.out.println("O campo Login é obrigatorio");
			resultado = false;
		}
		if(usuarioVo.getSenha().isEmpty()||usuarioVo.getSenha()==null) {
			System.out.println("O campo senha é obrigatorio");
			resultado = false;
		}
		return resultado;
	}




	private void excluirUsuario() {
		UsuarioVo usuarioVo = new UsuarioVo();
		System.out.println("\nDigite o código do ususario");
		usuarioVo.setIdusuario(Integer.parseInt(teclado.nextLine()));
		System.out.println("Digite a data de hoje: ");
		usuarioVo.setDataExpiracao(LocalDate.parse(teclado.nextLine(),dateFormatter));

		UsuarioController usuarioController = new UsuarioController();
		boolean resultado = usuarioController.excluirUsuarioController(usuarioVo);

		if(resultado) {
			System.out.println("\nUsuario excluido com sucesso");
		}else {
			System.out.println("\nNão foi possivel excluir o usuário");
		}

	}



	private void atualizarUsuario() {
		UsuarioVo usuarioVo= new UsuarioVo();

		System.out.println("\nDigite o código do usuario");
		usuarioVo.setIdusuario(Integer.parseInt(teclado.nextLine()));


		do {
			usuarioVo.setTipoUsuarioVO(TipoUsuarioVO.getTipoUsuarioVoPorValor(this.apresentarOperacoesTipoUsuario()));
		}while(usuarioVo.getTipoUsuarioVO()==null);

		System.out.println("\nDigite o nome do usuario");
		usuarioVo.setNome(teclado.nextLine());
		System.out.println("Digite o cpf do usuario");
		usuarioVo.setCpf(teclado.nextLine());
		System.out.println("\nDigite o email do usuario");
		usuarioVo.setEmail(teclado.nextLine());

		System.out.println("\nDigite a data de cadastro");
		usuarioVo.setDataCadastro(LocalDate.parse(teclado.nextLine(),dateFormatter));

		System.out.println("Digite o login");
		usuarioVo.setLogin(teclado.nextLine());
		System.out.println("digite a senha");
		usuarioVo.setSenha(teclado.nextLine());

		if(this.validarCamposCadastro(usuarioVo)) {
			UsuarioController usuarioController = new UsuarioController();
			boolean resultado=usuarioController.atualizarUsuarioController(usuarioVo);

			if(resultado) {
				System.out.println("usuario atualizado com sucesso");
			}else {
				System.out.println("não foi possivel atualizar usuario");
			}

		}

	}


// começando pelo menu 1
	private void consultarUsuario() {
		int opcao = this.apresentarOpcoesDeConsulta();
		UsuarioController  usuarioController = new UsuarioController();
		while(opcao != OPCAO_MENU_CONSULTAR_USUARIO_SAIR) {
			switch(opcao) {
			case OPCAO_MENU_CONSULTAR_TODOS_USUARIOS:{
				opcao=OPCAO_MENU_CONSULTAR_USUARIO_SAIR;
				ArrayList<UsuarioVo> listaUsuarioVo= usuarioController.consultarTodosUsuariosController();
				System.out.println("\n---------------RESULTADO DA CONSULTA-------------------");
				System.out.printf("\n%3s  %-13s %-20s  5-11s  %-20s  %-15s  %-10s  %-10s","id","TIPO USUARIO","NOME","CPF","EMAIL","DATA CADASTRO","DATA EXPIRAÇÃO","LOGN","SENHA");
				for(int i = 0;i<listaUsuarioVo.size();i++) {
						listaUsuarioVo.get(i).imprimir();
				}
				
				System.out.println();
				break;
			}
			case OPCAO_MENU_CONSULTAR_UM_USUARIO:{
				
				opcao=OPCAO_MENU_CONSULTAR_USUARIO_SAIR;
				
				UsuarioVo usuarioVo= new UsuarioVo();
				System.out.println("informe o código do usuario");
				usuarioVo.setIdusuario(Integer.parseInt(teclado.nextLine()));
				
				usuarioVo =usuarioController.consultarUsuarioController(usuarioVo);
				System.out.println("\n---------------RESULTADO DA CONSULTA-------------------");
				System.out.printf("\n%3s  %-13s %-20s  5-11s  %-20s  %-15s  %-10s  %-10s","id","TIPO USUARIO","NOME","CPF","EMAIL","DATA CADASTRO","DATA EXPIRAÇÃO","LOGN","SENHA");
				
				usuarioVo.imprimir();
				System.out.println();
				
				break;
			}
			default:{
				System.out.println("\nOpção invalida");
				opcao=this.apresentarOpcoesDeConsulta();
				break;
			}
			}
		}
	}
	
		
	




	private int apresentarOpcoesDeConsulta() {
	System.out.println("\nInformo o tipo de consulta a ser realizado");
	System.out.println(OPCAO_MENU_CONSULTAR_TODOS_USUARIOS+"--consultar todos os usuarios--");
	System.out.println(OPCAO_MENU_CONSULTAR_UM_USUARIO+"--consultar um usuario especifico--");
	System.out.println(OPCAO_MENU_CONSULTAR_USUARIO_SAIR+"--SAIR--");
	System.out.println("\nDigite uma opção");
	
		return Integer.parseInt(teclado.nextLine());
	}




}
