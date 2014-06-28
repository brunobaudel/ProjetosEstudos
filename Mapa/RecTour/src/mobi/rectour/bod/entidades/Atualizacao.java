package mobi.rectour.bod.entidades;

public class Atualizacao {
	
	private String nome_tabela  ;
	private String dataUltimaAtualizacao; 	
	private String determinarAtualizacao;
	
	
	public Atualizacao(){
		nome_tabela = "";
		dataUltimaAtualizacao = "";
		determinarAtualizacao = "S";
	}
	
	
	public String getNome_tabela() {
		return nome_tabela;
	}
	public void setNome_tabela(String nome_tabela) {
		this.nome_tabela = nome_tabela;
	}
	public String getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}
	public void setDataUltimaAtualizacao(String dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}
	public String getDeterminarAtualizacao() {
		return determinarAtualizacao;
	}
	public void setDeterminarAtualizacao(String determinarAtualizacao) {
		this.determinarAtualizacao = determinarAtualizacao;
	}   	
	
	

}
