package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.RelatorioController;
import model.dto.RelatorioDTO;

public class MenuRelatorio {
	Scanner teclado = new Scanner(System.in);
	private static final int OPCAO_RELATORIO=1;
	private static final int OPCAO_RELATORIO_SAIR=9;

	public void apresentarMenuRelatorio() {

		int opcao = this.apresentarOpcaoMenu();
		while(opcao!=OPCAO_RELATORIO_SAIR) {
			switch(opcao) {
			case OPCAO_RELATORIO:{ 
				RelatorioController relatorioController = new RelatorioController();
				ArrayList<RelatorioDTO> resultadoRelatorio = relatorioController.consultarUsuariosDoChamadoFechado();
				break;
			}
			default:{
				System.out.println("Opção Invalida");
			}
			}
			opcao=apresentarOpcaoMenu();
		}
	}

	private int apresentarOpcaoMenu() {
		System.out.println("\n--Sitema Relatorio--");
		System.out.println("\n--Menu Relatorios--");
		System.out.println("\nOpções");
		System.out.println(OPCAO_RELATORIO+"realizar realotorios");

		System.out.println(OPCAO_RELATORIO_SAIR+"sair");
		System.out.println("");
		return Integer.parseInt(teclado.nextLine());

	}
}