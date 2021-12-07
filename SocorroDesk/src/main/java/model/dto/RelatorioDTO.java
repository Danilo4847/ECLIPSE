package model.dto;

public class RelatorioDTO {

	private int idChamado;
	private String nomeTecnico;
	private String nomeCriador;

	public RelatorioDTO(int idChamado, String nomeTecnico,String nomeCriador) {
		super();
		this.idChamado = idChamado;
		this.nomeTecnico = nomeTecnico;
		this.nomeCriador = nomeCriador;
	}
	public RelatorioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getIdChamado() {
		return idChamado;
	}
	public void setIdChamado(int idChamado) {
		this.idChamado = idChamado;
	}

	public String getnomeTecnico() {
		return nomeCriador;
	}
	public void setnomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

	public String getNomeCriador() {
		return nomeCriador;
	}
	public void setNomeCriador(String nomeCriador) {
		this.nomeCriador = nomeCriador;
	}


	public void imprimir() {
		System.out.printf("\n%10s  %10s  %10s"
				,this.getIdChamado(),this.getnomeTecnico(),this.getNomeCriador());

	}
}
