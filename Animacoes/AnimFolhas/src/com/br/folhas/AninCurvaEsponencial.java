package com.br.folhas;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AninCurvaEsponencial {
	
	
	private float xAnd;
	private float yAnd;
	private float xAnd_Anterior;
	private float yAnd_Anterior;
	private float x ;
	private float y ;
	private float fator = 0;
	private float a;
	private Animation anTranslate;
	private int width;
	private ImageView viewAnimar;
	
	private int tamImgEnviado;
	
	public void setA(float a) {
		this.a = a;
	}

	public AninCurvaEsponencial(int width,ImageView view, float a){
		xAnd_Anterior = 0;
		yAnd_Anterior = 0; 
		xAnd = 0;
		yAnd = 0;
		this.width = width;
		this.viewAnimar = view;
		this.a = a;
	}
	
	public float getxAnd() {
		return xAnd;
	}
	
	public void initAnin(int tamImg){
		
		tamImgEnviado = tamImg = 0;
		
		calcularXYCurvaEsponencial(fator);
		
		anTranslate = new TranslateAnimation(Animation.ABSOLUTE,  width - xAnd_Anterior - tamImg,   
  											 Animation.ABSOLUTE,  width - xAnd - tamImg ,
											 Animation.ABSOLUTE,  yAnd_Anterior,   
											 Animation.ABSOLUTE,  yAnd);
		
		anTranslate.setAnimationListener(an);
		anTranslate.setDuration(30);
		anTranslate.setRepeatCount(1);
		this.fator = this.fator + 0.1f;
		
		viewAnimar.startAnimation(anTranslate);
	}
	
	
	private void calcularXYCurvaEsponencial(float fator){
		xAnd_Anterior = xAnd;
		yAnd_Anterior = yAnd;
		x =   fator;
		xAnd = x * 30; 
		//a = 1.5f;  
 		y =  (float) Math.pow(a, x);
		yAnd =  y * 2;
	}
	
	
	private AnimationListener an = new AnimationListener() {
		
		@Override
		public void onAnimationStart(Animation animation) {
			
		}
	
		@Override
		public void onAnimationRepeat(Animation animation) {
			if(yAnd < width/2)
				initAnin(tamImgEnviado);
				
		
		}
		
		@Override
		public void onAnimationEnd(Animation animation) {
		
			xAnd_Anterior = 0;
			yAnd_Anterior = 0; 
			xAnd = 0;
			yAnd = 0;
			fator = 0;
			initAnin(tamImgEnviado);
			
		}
	};
	
}
