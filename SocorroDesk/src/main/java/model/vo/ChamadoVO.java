package model.vo;

import java.time.LocalDate;


public class ChamadoVO {

	private int idtecnico;
	private int idchamado;
	private int idusuario;
	private String titulo;
	private String descricao;
	private LocalDate data;
	private String solucao;
	private LocalDate dataFechamaneto;
	

	

	public ChamadoVO() {
		super();
		// TODO Auto-generated constructor stub
	}




	public ChamadoVO(int idtecnico, int idchamado, int idusuario, String titulo, String descricao, LocalDate data,
			String solucao, LocalDate dataFechamaneto) {
		super();
		this.idtecnico = idtecnico;
		this.idchamado = idchamado;
		this.idusuario = idusuario;
		this.titulo = titulo;
		this.descricao = descricao;
		this.data = data;
		this.solucao = solucao;
		this.dataFechamaneto = dataFechamaneto;
	}




	public int getIdtecnico() {
		return idtecnico;
	}




	public void setIdtecnico(int idtecnico) {
		this.idtecnico = idtecnico;
	}




	public int getIdchamado() {
		return idchamado;
	}




	public void setIdchamado(int idchamado) {
		this.idchamado = idchamado;
	}




	public int getIdusuario() {
		return idusuario;
	}




	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}




	public String getTitulo() {
		return titulo;
	}




	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}




	public String getDescricao() {
		return descricao;
	}




	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




	public LocalDate getData() {
		return data;
	}




	public void setData(LocalDate data) {
		this.data = data;
	}




	public String getSolucao() {
		return solucao;
	}




	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}




	public LocalDate getDataFechamaneto() {
		return dataFechamaneto;
	}




	public void setDataFechamaneto(LocalDate dataFechamaneto) {
		this.dataFechamaneto = dataFechamaneto;
	}




	public void imprimir() {
		System.out.printf("\n%10s  %10s  %10s  %-30s  %-50s  %-15s  %-30s  %-15s "
				,this.getIdchamado(), this.getIdusuario(), this.getIdtecnico(), this.getTitulo(), this.getDescricao(),
				this.getData(), this.getSolucao(), this.getDataFechamaneto());
				
	}

	
	
	
	
	
}
