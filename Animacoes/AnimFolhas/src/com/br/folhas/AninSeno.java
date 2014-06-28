package com.br.folhas;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AninSeno {
	
	
	private int xAnd;
	private int yAnd;
	private int xAnd_Anterior;
	private int yAnd_Anterior;
	private double x ;
	private double y ;
	private double fator = 0;
	private double fatorEnviado;
	private Animation anTranslate;
	private int width;
	private ImageView viewAnimar;
	
	private int alturaEnviado;
	private int larguraEnviado;
	private int tamImgEnviado;
	private int qtdAnin;
	private int qtdAninFinalizadas;
	
	public AninSeno(int width){
		 xAnd_Anterior = 0;
	     yAnd_Anterior = 0; 
	     xAnd = 0;
	     yAnd = 0;
	     this.width = width;
	     
	}
	public AninSeno(int width,ImageView view){
		xAnd_Anterior = 0;
		yAnd_Anterior = 0; 
		xAnd = 0;
		yAnd = 0;
		this.width = width;
		this.viewAnimar = view;
		qtdAnin = 0;
		qtdAninFinalizadas = 0 ;
	}
	public int getxAnd() {
		return xAnd;
	}
	public  Animation getAninConfig(AnimationListener aninListner, double percentFator,int altura,int largura){
		
		
		calcularXY(fator,altura,largura);
		
		anTranslate = new TranslateAnimation(Animation.ABSOLUTE, width - xAnd_Anterior,   
									         Animation.ABSOLUTE, width - xAnd,
									         Animation.ABSOLUTE,  yAnd_Anterior,   
									         Animation.ABSOLUTE,  yAnd);
		
		anTranslate.setAnimationListener(aninListner);
		anTranslate.setDuration(30);
		fator = fator + percentFator;
		
		
		return anTranslate;
	}
	
	public void initAnin( double percentFator,int altura,int largura,int tamImg){
		
		fatorEnviado = percentFator;
		alturaEnviado = altura;
		larguraEnviado = largura;
		tamImgEnviado = tamImg;
		
		calcularXY(fator,altura,largura);
		
		anTranslate = new TranslateAnimation(Animation.ABSOLUTE, width - xAnd_Anterior + tamImg,   
				Animation.ABSOLUTE, width - xAnd + tamImg,
				Animation.ABSOLUTE,  yAnd_Anterior,   
				Animation.ABSOLUTE,  yAnd);
		
		anTranslate.setAnimationListener(an);
		anTranslate.setDuration(30);
		anTranslate.setRepeatCount(1);
		fator = fator + percentFator;
		
		
		viewAnimar.startAnimation(anTranslate);
		
	}
	
	
	
	public void start(){
		anTranslate.start();
	}
	
	private void calcularXY(double fator,int altura,int largura){
		xAnd_Anterior = xAnd;
	    yAnd_Anterior = yAnd; 
		x = getFatorX(fator);
		xAnd = (int)(x * largura); 
        y =  Math.sin(x);
        yAnd = (int) (y * altura);
	}
	
	private double getFatorX(double x){
		double fator = (x * Math.PI)/2;
		return fator;
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
	
	
	private AnimationListener an = new AnimationListener() {
		
		@Override
		public void onAnimationStart(Animation animation) {
			
		}
		
		@Override
		public void onAnimationRepeat(Animation animation) {
			if(xAnd < width + (tamImgEnviado * 2)){
				initAnin( fatorEnviado, alturaEnviado, larguraEnviado,tamImgEnviado);
				//qtdAnin++;
			}
		}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			qtdAninFinalizadas = 0;
			qtdAnin = 0 ;
			xAnd_Anterior = 0;
			yAnd_Anterior = 0; 
			xAnd = 0;
			yAnd = 0;
			fator = 0;
			initAnin( fatorEnviado, alturaEnviado, larguraEnviado,tamImgEnviado);
				
			
			//
		}
	};
	
}
