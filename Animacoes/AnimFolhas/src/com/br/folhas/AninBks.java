package com.br.folhas;

import java.util.List;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AninBks {
	
	private List<ImageView> viewAnimar;
	private int width;
	private int listaCircular;
	
	public AninBks(List<ImageView> view){
		this.viewAnimar = view;
		this.width = -100;
		listaCircular = 0;
	}
	
	
	public void initAnin(){
		this.width = this.width > 0 ? getWidth()  : 0;
		Animation a = new TranslateAnimation(Animation.ABSOLUTE, 0,   
		         Animation.ABSOLUTE, width ,//tamImage,   
		         Animation.ABSOLUTE, 0,   
		         Animation.ABSOLUTE, 0);
		a.setDuration(3000); 
		//a.setRepeatCount(1);
		a.setAnimationListener(an);
		
		viewAnimar.get(0).startAnimation(a);
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}
	
	
	
	private AnimationListener an = new AnimationListener() {
		
		@Override
		public void onAnimationStart(Animation animation) {
			
		}
		
		@Override
		public void onAnimationRepeat(Animation animation) {
			
			
		}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			switch ( listaCircular % 2) {
			case 0:
				Animation b = new TranslateAnimation(Animation.ABSOLUTE, width  - viewAnimar.get(1).getWidth(),   //- tamImage   - tamImage
						Animation.ABSOLUTE, 0 - viewAnimar.get(1).getWidth(),   
						Animation.ABSOLUTE, 0,   
						Animation.ABSOLUTE, 0);
				b.setDuration(3000);
				b.setAnimationListener(an);
				viewAnimar.get(1).startAnimation(b);
				
				break;

			case 1:
					initAnin();
				break;
	
			default:
				break;
			}
			
			listaCircular++;
			
		}
	};

}
