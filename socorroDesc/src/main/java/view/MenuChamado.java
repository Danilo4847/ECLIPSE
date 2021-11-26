package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import modem.vo.ChamadoVO;
import modem.vo.UsuarioVo;

public class MenuChamado {

	
	Scanner teclado= new Scanner(System.in);
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	private static final int OPCAO_REALIZAR_CHAMADO=1;
	
	public void apresentarMenuChamado() {
	
		ChamadoVO chamadoVO= new ChamadoVO();
		this.cadastrarUsuario(chamadoVO);
		
		
		
	}

	private void cadastrarUsuario(ChamadoVO chamadoVO) {

		System.out.println("titulo");
		chamadoVO.setTitulo(teclado.nextLine());
		chamadoVO.setData(LocalDate.now());
		System.out.println("solução");
		chamadoVO.setSolucao(teclado.nextLine());
		chamadoVO.setDataFechamaneto(LocalDate.now());
	
	}

}
