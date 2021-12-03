package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import controller.ChamadoController;
import model.vo.ChamadoVO;


public class MenuChamado {

	
	Scanner teclado= new Scanner(System.in);
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	private static final int OPCAO_REALIZAR_CHAMADO=1;
	private static final int OPCAO_CONSULTAR_CHAMADO=2;
	private static final int OPCAO_ATUALIZAR_CHAMADO=3;
	private static final int OPCAO_EXCLUIR_CHAMADO=4;
	private static final int OPCAO_SAIR=9;
	
	
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
					this.consultarChamado();
					break;
				}
				case OPCAO_ATUALIZAR_CHAMADO:{
					this.atualizarChamado();
					break;
				}
				case OPCAO_EXCLUIR_CHAMADO:{
					this.excluirChamado();
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

	private void excluirChamado() {
		// TODO Auto-generated method stub
		
	}

	private void atualizarChamado() {
		// TODO Auto-generated method stub
		
	}

	private void consultarChamado() {
		// TODO Auto-generated method stub
		
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
				chamadoVO.setDescriao(teclado.nextLine());
				
				
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
			if(chamadoVO.getDescriao().isEmpty()||chamadoVO.getDescriao()==null) {
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




	


		
		
		
	

}
