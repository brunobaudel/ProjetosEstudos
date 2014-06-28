package com.br.folhas;




import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;



public class AnimFolhasActivity extends Activity {
	
	
	private ImageView folha1;
	private ImageView folha2;
	
	
	private int width;
	
	
	
	private AninSeno aninFolha1;
	private AninSeno aninFolha2;
	
	private AninCurvaEsponencial aninFolha1_Curva;
	private AninCurvaEsponencial aninFolha2_Curva;
	
	private AsyncTask<Void, Void, Void> at ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		folha1 = (ImageView)findViewById(R.id.imgFolha);
		folha2 = (ImageView)findViewById(R.id.imgFolha1);
		
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        width = display.getWidth();  
        			
		at = new OperacaoChamarLogin();
		
		aninFolha1_Curva = new AninCurvaEsponencial(width,folha1,1.6f);
		aninFolha2_Curva = new AninCurvaEsponencial(width,folha2,1.7f);
		
		//aninFolhaAndarFrente.setAnimationListener(this);
		
		//folha1.setAnimation(aninFolha1.getAninConfig(anFolha1, 0.3, 50, 30));
		//folha2.setAnimation(aninFolha2.getAninConfig(anFolha2, 0.03, 80, 30));
		
	}
	
	
	
	@Override
	protected void onStart() {
		super.onStart();
		at.execute();
	}
	
	public class OperacaoChamarLogin extends AsyncTask<Void, Void, Void>  {

		ProgressDialogPersonalisadoMOBC dialog = new ProgressDialogPersonalisadoMOBC(AnimFolhasActivity.this);
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.setMsgDialog("Ativando Tiquete !");
			dialog.setCancelable(false);
			dialog.setIndeterminate(false);
			
			dialog.show();
			
			try {
				
				Thread.sleep(900);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			aninFolha1_Curva.initAnin(folha1.getWidth());
			aninFolha2_Curva.initAnin(folha2.getWidth());
			
			
		}
		
		
		@Override
		protected Void doInBackground(Void... v) {
			try {
				
				Thread.sleep(10000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return null;
			
		}
		
		@Override
		protected void onPostExecute(Void v) {
			//aninFolhaAndarFrente.start();
			dialog.dismiss();
			if(!isCancelled()){
				
				//overridePendingTransition(android.R.anim.fade_in,
				//		android.R.anim.fade_out);
			}	
		}
		
	}
	
	
	
	
	AnimationListener anFolha1 = new AnimationListener() {
		
		@Override
		public void onAnimationStart(Animation animation) {
			
		}
		
		@Override
		public void onAnimationRepeat(Animation animation) {
			
		}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			folha1.setAnimation(aninFolha1.getAninConfig(anFolha1, 0.08f, 40, 60));
			//folha1.setTag(new EstadoAnimacao(aninFolha1.getxAnd_Anterior(), aninFolha1.getyAnd_Anterior()));
			
		}
	};
	
	AnimationListener anFolha2 = new AnimationListener() {
		
		@Override
		public void onAnimationStart(Animation animation) {
			
		}
		
		@Override
		public void onAnimationRepeat(Animation animation) {
			
		}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			folha2.setAnimation(aninFolha2.getAninConfig(anFolha2, 0.1f, 30, 60));
			
			Animation ani = new RotateAnimation(0, 90,   
		            Animation.RELATIVE_TO_SELF, 0.5f,   
		            Animation.RELATIVE_TO_SELF, 0.5f);
			ani.setRepeatCount(5); 
			folha2.setAnimation(ani);  
			ani.start();
		
			
		}
	};
	

	
}
