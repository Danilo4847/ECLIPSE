package modem.vo;

import java.time.LocalDate;


public class ChamadoVO {

	
	private int idchamado;
	private UsuarioVo idusuario;
	private String titulo;
	private String descriao;
	private LocalDate data;
	private String solucao;
	private LocalDate dataFechamaneto;
	
	
	public ChamadoVO(int idchamado, UsuarioVo idusuario, String titulo, String descriao, LocalDate data, String solucao,
			LocalDate dataFechamaneto) {
		super();
		this.idchamado = idchamado;
		this.idusuario = idusuario;
		this.titulo = titulo;
		this.descriao = descriao;
		this.data = data;
		this.solucao = solucao;
		this.dataFechamaneto = dataFechamaneto;
	}


	public ChamadoVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getIdchamado() {
		return idchamado;
	}


	public void setIdchamado(int idchamado) {
		this.idchamado = idchamado;
	}


	public UsuarioVo getIdusuario() {
		return idusuario;
	}


	public void setIdusuario(UsuarioVo idusuario) {
		this.idusuario = idusuario;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescriao() {
		return descriao;
	}


	public void setDescriao(String descriao) {
		this.descriao = descriao;
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
	
	
	
	
	
	
	
	
}