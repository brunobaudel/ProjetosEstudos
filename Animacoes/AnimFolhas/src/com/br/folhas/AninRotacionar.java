package com.br.folhas;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class AninRotacionar {

	private ImageView viewAnimar;
	
	
	public AninRotacionar(ImageView view){
		this.viewAnimar = view;
	}
	
	
	public void initAnin(){
		
		Animation a = new RotateAnimation(0, 360,
										  Animation.RELATIVE_TO_SELF, 0.5f,
										  Animation.RELATIVE_TO_SELF, 0.5f);
		a.setRepeatCount(Animation.INFINITE);
		
		a.setDuration(4000);
		viewAnimar.startAnimation(a);
		
	}
	

	
}
