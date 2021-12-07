package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ChamadoController;
import model.vo.ChamadoVO;
import model.vo.UsuarioVO;


public class MenuChamado {

	
	Scanner teclado= new Scanner(System.in);
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	private static final int OPCAO_REALIZAR_CHAMADO=1;
	private static final int OPCAO_CONSULTAR_CHAMADO=2;
	private static final int OPCAO_ATUALIZAR_CHAMADO=3;
	private static final int OPCAO_EXCLUIR_CHAMADO=4;
	private static final int OPCAO_SAIR=9;
	
	private static final int OPCAO_MENU_CONSULTAR_TODOS_CHAMADOS = 1;
	private static final int OPCAO_MENU_CONSULTAR_CHAMADOS_ABERTOS = 2;
	private static final int OPCAO_MENU_CONSULTAR_CHAMADOS_FECHADOS = 3;
	private static final int OPCAO_MENU_CONSULTAR_SAIR = 9;
	
	public void apresentarMenuChamado() {
	
			int opcao=this.apresentarOpcaoMenu();


			while(opcao!=OPCAO_SAIR) {


				switch(opcao) {
				case OPCAO_REALIZAR_CHAMADO:{
					ChamadoVO chamadoVO= new ChamadoVO();
					this.cadastrarChamado(chamadoVO);
					break;
				}
				case OPCAO_CONSULTAR_CHAMADO:{
					UsuarioVO usuarioVO = new UsuarioVO();
					this.consultarChamado(usuarioVO);
					break;
				}
				case OPCAO_ATUALIZAR_CHAMADO:{
					UsuarioVO usuarioVO = new UsuarioVO();
					this.atualizarChamado(usuarioVO);
					break;
				}
				case OPCAO_EXCLUIR_CHAMADO:{
					UsuarioVO usuarioVO = new UsuarioVO();
					this.excluirChamado(usuarioVO);
					break;

				}
				default:{
					System.out.println("\nOpção invalida");
					break;
				}
				}
				opcao=this.apresentarOpcaoMenu();
			}
		}


	private int apresentarOpcaoMenu() {
		System.out.println("\n---Sistema Socorro Desk---");
		System.out.println("\n---Menu realizar chamado---");
		System.out.println(OPCAO_REALIZAR_CHAMADO+"-Realizar chamado-");
		System.out.println(OPCAO_CONSULTAR_CHAMADO+"-Consultar chamado-");
		System.out.println(OPCAO_ATUALIZAR_CHAMADO+"-Atualizar chamado-");
		System.out.println(OPCAO_EXCLUIR_CHAMADO+"-Excluir chamado-");
		System.out.println(OPCAO_SAIR+"-sair-");
		
		
		
		System.out.print("\nDigite a opção");
		return Integer.parseInt(teclado.nextLine());

	}



		private void cadastrarChamado(ChamadoVO chamadoVO) {

				System.out.println("\nDigite o titulo");
				chamadoVO.setTitulo(teclado.nextLine());
				
				
				System.out.println("Digite a descrição");
				chamadoVO.setDescricao(teclado.nextLine());
				
				
				chamadoVO.setData(LocalDate.now());
				System.out.println("digite a data");
				
				

				if(this.validarCamposChamados(chamadoVO)) {
					ChamadoController chamadoController = new ChamadoController();
					chamadoVO=chamadoController.cadastrarChamadoController(chamadoVO);

					if(chamadoVO.getIdchamado()!=0) {
						System.out.println("chamado realizado");
					}else {
						System.out.println("não foi possivel realizar chamado");
					}

				}

			
		
	}

		private boolean validarCamposChamados(ChamadoVO chamadoVO) {
			boolean resultado=true;

			System.out.println();
			if(chamadoVO.getTitulo().isEmpty()||chamadoVO.getTitulo()==null) {
				System.out.println("O campo nome é obrigatorio");
				resultado = false;

			}
			if(chamadoVO.getDescricao().isEmpty()||chamadoVO.getDescricao()==null) {
				System.out.println("O campo de descrição é obrigatorio");
				resultado = false;
				return resultado;
			}
		
			if(chamadoVO.getData()==null) {
				System.out.println("O campo data cadastro é obrigatorio");
				resultado = false;
			}
			
			return resultado;
		}


		private void consultarChamado(UsuarioVO usuarioVO) {
			int opcao = this.apresentarOpcoesConsulta();
			ChamadoController chamadoController = new ChamadoController();
			ChamadoVO chamadoVO = new ChamadoVO();
			chamadoVO.setIdusuario(usuarioVO.getIdusuario());
			
			while (opcao != OPCAO_MENU_CONSULTAR_SAIR) {
				switch (opcao) {
					case OPCAO_MENU_CONSULTAR_TODOS_CHAMADOS:{
						opcao = OPCAO_MENU_CONSULTAR_SAIR;
						ArrayList<ChamadoVO> listaChamadosVO = chamadoController.consultarTodosChamadosUsuarioController(chamadoVO);
						System.out.println("\n-------- RESULTADO DA CONSULTA --------");
						System.out.printf("\n%10s  %10s  %10s  %-30s  %-50s  %-15s  %-30s  %-15s  ", 
								"ID CHAMADO", "ID USUÃ�RIO", "ID TeCNICO", "TÃ�TULO", "DESCRIÃ‡ÃƒO", "DATA ABERTURA", "SOLUÃ‡ÃƒO", "DATA FECHAMENTO");
						for(ChamadoVO chamado: listaChamadosVO) {
							chamado.imprimir();
						}
						break;
					}
					case OPCAO_MENU_CONSULTAR_CHAMADOS_ABERTOS:{
						opcao = OPCAO_MENU_CONSULTAR_SAIR;
						ArrayList<ChamadoVO> listaChamadosAbertosVO = chamadoController.consultarChamadosAbertosUsuarioController(chamadoVO);
						System.out.println("\n-------- RESULTADO DA CONSULTA --------");
						System.out.printf("\n%10s  %10s  %10s  %-30s  %-50s  %-15s  %-30s  %-15s  ", 
								"ID CHAMADO", "ID USUÃ�RIO", "ID TÃ‰CNICO", "TÃ�TULO", "DESCRIÃ‡ÃƒO", "DATA ABERTURA", "SOLUÃ‡ÃƒO", "DATA FECHAMENTO");
						for(ChamadoVO chamado: listaChamadosAbertosVO) {
							chamado.imprimir();
						}
						break;
					}
					case OPCAO_MENU_CONSULTAR_CHAMADOS_FECHADOS:{
						opcao = OPCAO_MENU_CONSULTAR_SAIR;
						opcao = OPCAO_MENU_CONSULTAR_SAIR;
						ArrayList<ChamadoVO> listaChamadosFechadosVO = chamadoController.consultarChamadosFechadosController(chamadoVO);
						System.out.println("\n-------- RESULTADO DA CONSULTA --------");
						System.out.printf("\n%10s  %10s  %10s  %-30s  %-50s  %-15s  %-30s  %-15s  ", 
								"ID CHAMADO", "ID USUÃ�RIO", "ID TÃ‰CNICO", "TÃ�TULO", "DESCRIÃ‡ÃƒO", "DATA ABERTURA", "SOLUÃ‡ÃƒO", "DATA FECHAMENTO");
						for(ChamadoVO chamado: listaChamadosFechadosVO) {
							chamado.imprimir();
						}
						break;
					}
					default:{
						System.out.println("\nOpÃ§Ã£o invÃ¡lida!");
						opcao = this.apresentarOpcoesConsulta();
						break;
					}
				}
			}
			
		}

		private int apresentarOpcoesConsulta() {
			System.out.println("\nInforme o tipo de consulta a ser realizada");
			System.out.println(OPCAO_MENU_CONSULTAR_TODOS_CHAMADOS + " - Consultar todos os chamados");
			System.out.println(OPCAO_MENU_CONSULTAR_CHAMADOS_ABERTOS + " - Consultar chamados abertos");
			System.out.println(OPCAO_MENU_CONSULTAR_CHAMADOS_FECHADOS + " - Consultar chamados fechados");
			System.out.println(OPCAO_MENU_CONSULTAR_SAIR + " - Sair");
			System.out.print("\nDigite uma opÃ§Ã£o: ");
			return Integer.parseInt(teclado.nextLine());
		}

		private void atualizarChamado(UsuarioVO usuarioVO) {
			ChamadoVO chamadoVO = new ChamadoVO();
			System.out.print("\nDigite o cÃ³digo do chamado: ");
			chamadoVO.setIdchamado(Integer.parseInt(teclado.nextLine()));
			chamadoVO.setIdusuario(usuarioVO.getIdusuario());
			System.out.print("\nDigite o novo tÃ­tulo do chamado: ");
			chamadoVO.setTitulo(teclado.nextLine());
			System.out.print("\nDigite a nova descriÃ§Ã£o do chamado: ");
			chamadoVO.setDescricao(teclado.nextLine());
			chamadoVO.setData(LocalDate.now());

			boolean resultado = true;
			System.out.println();

			if(chamadoVO.getTitulo().isEmpty() || chamadoVO.getTitulo() == null) {
				System.out.println("O campo TÃ­tulo Ã© obrigatÃ³rio!");
				resultado = false;
			}
			if(chamadoVO.getDescricao().isEmpty() || chamadoVO.getDescricao() == null) {
				System.out.println("O campo descriÃ§Ã£o Ã© obrigatÃ³rio!");
				resultado = false;
			}

			if(resultado) {
				ChamadoController chamadoController = new ChamadoController();
				resultado = chamadoController.atualizarChamadoController(chamadoVO);

				if(resultado) {
					System.out.println("Chamado atualizado com sucesso!");
				}else {
					System.out.println("NÃ£o foi possÃ­vel atualizar o chamado.");
				}
			}
			
		}

		private void excluirChamado(UsuarioVO usuarioVO) {
			ChamadoVO chamadoVO = new ChamadoVO();
			System.out.print("\nDigite o código do chamado: ");
			chamadoVO.setIdchamado(Integer.parseInt(teclado.nextLine()));
			chamadoVO.setIdusuario(usuarioVO.getIdusuario());
			ChamadoController chamadoController = new ChamadoController();
			boolean resultado = chamadoController.excluirChamadoController(chamadoVO);

			if(resultado){
				System.out.println("\nChamado excluido com sucesso!");
			}else{
				System.out.println("\nNão foi possivel excluir o chamado!");
			}
			
		}

		private int apresentarOpcoesMenu() {
			System.out.println("\n---- Sistema Socorro Desk ----");
			System.out.println("\n---- Menu de Chamados ----");
			System.out.println(OPCAO_REALIZAR_CHAMADO + " - Cadastrar Chamado");
			System.out.println(OPCAO_CONSULTAR_CHAMADO + " - Consultar Chamado");
			System.out.println(OPCAO_ATUALIZAR_CHAMADO + " - Atualizar Chamado");
			System.out.println(OPCAO_EXCLUIR_CHAMADO + " - Excluir Chamado");
			System.out.println(OPCAO_SAIR + " - Sair");
			System.out.print("\nDigite a opção: ");
			return Integer.parseInt(teclado.nextLine());
		}
	


		
		
		


	


		
		
		
	

}
