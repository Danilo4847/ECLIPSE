package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ChamadoController;
import model.vo.ChamadoVO;
import model.vo.UsuarioVO;

public class MenuAtendimento {
	
	Scanner teclado = new Scanner(System.in);
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private static final int OPCAO_MENU_LISTA_CHAMADO=1;
	private static final int OPCAO_MENU_ATENDER_CHAMADO=2;
	private static final int OPCAO_MENU_CHAMADO_SAIR=3;
	
	private static final int OPCAO_MENU_LISTA_CHAMADOS_ABERTOS=4;
	private static final int OPCAO_MENU_LISTA_CHAMDOS_FECHADOS=5;
	private static final int OPCAO_MENU_LISTA_CHAMADOS_SAIR=9;
	
	

	public void apresentarMenuAtendimento(UsuarioVO usuarioVO) {

		int opcao = this.apresentarOpcaoMenu();
		while(opcao!=OPCAO_MENU_CHAMADO_SAIR) {
			switch(opcao) {
			case OPCAO_MENU_LISTA_CHAMADO:{ 
				this.listaChamados(usuarioVO);
				break;
			}
			case OPCAO_MENU_ATENDER_CHAMADO:{
				this.atenderChamados(usuarioVO);
				break;
			}
			default:{
				System.out.println("Opção Invalida");
			}
			}
			opcao=apresentarOpcaoMenu();
		}
		
	}






	private void listaChamados(UsuarioVO usuarioVO) {
int opcao = this.apresentarOpcoesConsulta();
ChamadoController chamadoController = new ChamadoController();
while(opcao!=OPCAO_MENU_LISTA_CHAMADOS_SAIR) {
	switch(opcao) {
	case OPCAO_MENU_LISTA_CHAMADOS_ABERTOS:{
		opcao=OPCAO_MENU_LISTA_CHAMADOS_SAIR;
		ArrayList<ChamadoVO>listaChamadosVO = chamadoController.listarChamadosAbertosController();
		System.out.print("\n------------------RESULTADO DE CONSULTA----------------------");
		System.out.printf("\n%10s  %10s  %-30s  %-50s  %-15s  %-30s   %-30s  %-15s","ID CHAMADO","ID USUARIO","ID TECNICO",
				"TITULO","DESCRICAO","DATA ABERTURA","SOLUCAO","DATA FECHAMENTO");
		for(int i = 0;i<listaChamadosVO.size();i++) {
			listaChamadosVO.get(i).imprimir();
		}
		System.out.println();
		break;
	}
	case OPCAO_MENU_LISTA_CHAMDOS_FECHADOS:{
		opcao=OPCAO_MENU_LISTA_CHAMADOS_SAIR;
		ArrayList<ChamadoVO>listaChamadosVO = chamadoController.consultarChamadosFechadosController(usuarioVO);
		System.out.print("\n------------------RESULTADO DE CONSULTA----------------------");
		System.out.printf("\n%10s  %10s  %-30s %-50s  %-15s  %-30s   %-30s  %-15s","ID CHAMADO","ID USUARIO","ID TECNICO",
				"TITULO","DESCRICAO","DATA ABERTURA","SOLUCAO","DATA FECHAMENTO");
		for(int i = 0;i<listaChamadosVO.size();i++) {
			listaChamadosVO.get(i).imprimir();
		}
		System.out.println();
		break;
	}
	default: {
		System.out.println("\nOpção invalida");
		opcao=this.apresentarOpcoesConsulta();
	}
	}
}
	}



	private int apresentarOpcoesConsulta() {
	System.out.println("\nInformar o tipo de consulta");
	System.out.println(OPCAO_MENU_LISTA_CHAMADOS_ABERTOS+"listar todos os chamdos");
	System.out.println(OPCAO_MENU_LISTA_CHAMDOS_FECHADOS+"listar todos os chamdos fechados");
	System.out.println(OPCAO_MENU_LISTA_CHAMADOS_SAIR+"sair");
	System.out.println("\nDigite a opção");
	return Integer.parseInt(teclado.nextLine());
	}



	private void atenderChamados(UsuarioVO usuarioVO) {
	ChamadoVO chamadoVO = new ChamadoVO();
	chamadoVO.setIdtecnico(usuarioVO.getIdusuario());
	System.out.println("\nDigite o codigo do chamado");
	chamadoVO.setIdchamado(Integer.parseInt(teclado.nextLine()));
	System.out.println("Digite a soluÃ§Ã£o");
	chamadoVO.setSolucao(teclado.nextLine());
	chamadoVO.setDataFechamaneto(LocalDate.now());
	
	if(chamadoVO.getIdchamado() == 0|| chamadoVO.getSolucao().isEmpty()) {
		System.out.println("Os campos devem ser preenchidos ");
	}else {
		if(chamadoVO.getDataFechamaneto()!=null) {
			System.out.println("atendimetnto fechado com sucesso");
		}else {
			System.out.println("erro ao fechar o atendimento");
		}
	}
	
	
	
	}



	private int apresentarOpcaoMenu() {
	System.out.println("\n--Sitema Atendimento--");
	System.out.println("\n--Menu Atendimento de chamados--");
	System.out.println("\nOpÃ§Ã£o");
	System.out.println(OPCAO_MENU_LISTA_CHAMADO+"Lista Chamados");
	System.out.println(OPCAO_MENU_ATENDER_CHAMADO+"Atender chamados");
	System.out.println(OPCAO_MENU_CHAMADO_SAIR+"sair");
	System.out.println("");
	return Integer.parseInt(teclado.nextLine());
	
	
	
	}




}