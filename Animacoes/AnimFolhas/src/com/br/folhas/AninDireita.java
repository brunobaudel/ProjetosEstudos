package com.br.folhas;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AninDireita {

	
	private ImageView viewAnimar;
	private int width;
	
	public AninDireita(ImageView view){
		this.viewAnimar = view;
		this.width = -100;
		
	}
	
	
	public void initAnin(){
		this.width = this.width > 0 ? getWidth()  : 0;
		Animation a = new TranslateAnimation(Animation.ABSOLUTE, 0,   
		         Animation.ABSOLUTE, width - 0,//tamImage,   
		         Animation.ABSOLUTE, 0,   
		         Animation.ABSOLUTE, 0);
		a.setDuration(3000); 
		a.setRepeatCount(Animation.INFINITE);
		viewAnimar.startAnimation(a);
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}
	
	
	
}
