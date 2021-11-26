package model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class UsuarioVO {

	DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private int idusuario;
	private TipoUsuarioVO tipoUsuarioVO;
	private String nome;
	private String cpf;
	private String email;
	private LocalDate dataCadastro;
	private LocalDate dataExpiracao;
	private String login;
	private String senha;

	public UsuarioVO(int idusuario, TipoUsuarioVO tipoUsuarioVO, String nome, String cpf, String email,
			LocalDate dataCadastro, LocalDate dataExpiracao, String login, String senha) {
		super();
		this.idusuario = idusuario;
		this.tipoUsuarioVO = tipoUsuarioVO;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataCadastro = dataCadastro;
		this.dataExpiracao = dataExpiracao;
		this.login = login;
		this.senha = senha;
	}

	public UsuarioVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public TipoUsuarioVO getTipoUsuarioVO() {
		return tipoUsuarioVO;
	}

	public void setTipoUsuarioVO(TipoUsuarioVO tipoIsuarioVO) {
		this.tipoUsuarioVO = tipoIsuarioVO;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDate dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void imprimir() {
	System.out.printf("\n%3s  %-13s %-20s  5-11s  %-20s  %-15s  %-10s  %-10s",
			this.getIdusuario(),
			this.getTipoUsuarioVO(),
			this.getNome(),
			this.getCpf(),
			this.getEmail(),
			this.validarData(this.getDataCadastro()),
			this.validarData(this.getDataExpiracao()),
			this.getLogin(),
			this.getSenha());
		
	}

	private String validarData(LocalDate data) {
		String resultado = "";
		if(data!=null){
		resultado = data.format(formaterDate);
		}
		return resultado;
	}





}
