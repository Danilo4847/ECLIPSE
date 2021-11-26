package modem.vo;

public enum TipoUsuarioVO {

	ADMINISTRADOR(1),
	TECNICO(2),
	USUARIO(3);
	
	private int valor;
	
	TipoUsuarioVO(int valor){
		this.valor=valor;
		
	}

	public int getValor() {
		return valor;
	}
	
	public static TipoUsuarioVO getTipoUsuarioVoPorValor(int valor) {
	TipoUsuarioVO tipoUsuarioVo= null;
	for(TipoUsuarioVO elemento: TipoUsuarioVO.values()){
		if(elemento.getValor()==valor) {
			tipoUsuarioVo=elemento;
		}
	}
	return tipoUsuarioVo;
	}
}
