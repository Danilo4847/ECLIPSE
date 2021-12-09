package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.RelatorioController;
import model.dto.RelatorioDTO;
import model.vo.UsuarioVO;

public class MenuRelatorio {
	Scanner teclado = new Scanner(System.in);
	private static final int OPCAO_RELATORIO=1;
	private static final int OPCAO_RELATORIO_SAIR=9;

	public void apresentarMenuRelatorio(UsuarioVO usuarioVO) {

		int opcao = this.apresentarOpcaoMenu();
		while(opcao!=OPCAO_RELATORIO_SAIR) {
			switch(opcao) {
			case OPCAO_RELATORIO:{ 
				RelatorioController relatorioController = new RelatorioController();
				ArrayList<RelatorioDTO> resultadoRelatorio = relatorioController.consultarUsuariosDoChamadoFechado();
				System.out.println("\n-------- RESULTADO DA CONSULTA --------");
				System.out.printf("\n%10s  %10s  %10s","ID CHAMADO", "ID USUARIO", "NOME");
				for(RelatorioDTO relatorioDTO: resultadoRelatorio) {
					relatorioDTO.imprimir();
				}
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
		System.out.println();
		System.out.println("\n--Sitema Relatorio--");
		System.out.println("\n--Menu Relatorios--");
		System.out.println("\nOpções");
		System.out.println(OPCAO_RELATORIO+"realizar realotorios");

		System.out.println(OPCAO_RELATORIO_SAIR+"sair");
		System.out.println("");
		return Integer.parseInt(teclado.nextLine());

	}
}