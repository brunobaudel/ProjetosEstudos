package mobi.rectour.login.gui;

import mobi.rectour.R;
import mobi.rectour.menuprincipal.gui.MenuContainer;
import mobi.rectour.util.AninRotacionar;
import mobi.rectour.util.BaseActivity;
import mobi.rectour.util.BaseActivity.IAsyncTask;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashAct extends BaseActivity implements OnTouchListener,IAsyncTask {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_tela);
		
		initComponentes();
		startTask(null);
	}

	protected void onPause() {
		super.onPause();
		finish();
	}

	public void preExecute(Bundle parametro) {
		
		
	}

	public void execute(Bundle parametro) throws Exception {
		try {
			
			
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void updateView(Bundle parametro) {

		if (!getIsCancelled()) {
			mudarTela();
		}
	}

	@Override
	public void initComponentes() {
		((RelativeLayout) findViewById(R.id.rlSplash)).setOnTouchListener(this);

		ImageView rodaZae = (ImageView) findViewById(R.telasplash.imgSplash);
		AninRotacionar aninRotacao = new AninRotacionar(rodaZae);
		aninRotacao.initAnin();
	}

	@Override
	public IAsyncTask getTransactionTask() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
			
		if (event.getAction() == MotionEvent.ACTION_UP  ) {
			mudarTela();
		}

		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		getTransactionTaskClass().cancel(true);
		// mudarTela();//Tirar depoisssss
	}

	private void mudarTela() {
		getTransactionTaskClass().cancel(true);
		Intent it = new Intent(SplashAct.this, MenuContainer.class);
		startActivity(it);

		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
	}

}
