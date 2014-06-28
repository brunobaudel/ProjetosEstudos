package mobi.rectour.configuracao.selecionaIconLocation.entidades;

public class IconLocation {

	private String nomeIcone;
	private int    idIcone;
	private int ordemSelecionado;
	private boolean selecionado;
	
	public IconLocation(String nomeIcone, int idIcone , int ordemSelecao, boolean selecionado) {
		super();
		this.nomeIcone = nomeIcone;
		this.idIcone = idIcone;
		this.ordemSelecionado = ordemSelecao;
		this.selecionado = selecionado;
	}
	
	public String getNomeIcone() {
		return nomeIcone;
	}
	public void setNomeIcone(String nomeIcone) {
		this.nomeIcone = nomeIcone;
	}
	public int getIdIcone() {
		return idIcone;
	}
	public void setIdIcone(int idIcone) {
		this.idIcone = idIcone;
	}

	public int getOrdemSelecionado() {
		return ordemSelecionado;
	}

	public void setOrdemSelecionado(int ordemSelecionado) {
		this.ordemSelecionado = ordemSelecionado;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	
	
	public boolean selecionado(String nomeIcon){
		
		
		return nomeIcon.equals(nomeIcone) ;
		
	}
	
	
}
