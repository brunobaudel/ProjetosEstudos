package com.br.folhas;

public class EstadoAnimacao {
	private int xAnd_Anterior;
	private int yAnd_Anterior;
	
	
	public EstadoAnimacao(int x , int y){
		xAnd_Anterior = x;
		yAnd_Anterior = y;
	}


	public int getxAnd_Anterior() {
		return xAnd_Anterior;
	}


	public void setxAnd_Anterior(int xAnd_Anterior) {
		this.xAnd_Anterior = xAnd_Anterior;
	}


	public int getyAnd_Anterior() {
		return yAnd_Anterior;
	}


	public void setyAnd_Anterior(int yAnd_Anterior) {
		this.yAnd_Anterior = yAnd_Anterior;
	}
	
	
	
}
