package mobi.rectour.util;

import mobi.rectour.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

public class TypeFaceButton extends Button {
	
	private boolean appInstalado;
	private Intent itVerificacao;

	public TypeFaceButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public TypeFaceButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		setAppInstalado(false);
		TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TypeFaceButton);
        String s = a.getString(R.styleable.TypeFaceButton_nome_app);
		
		itVerificacao = context.getPackageManager().getLaunchIntentForPackage(s);
		
		
		if(itVerificacao != null){
			setBackgroundResource(getResourceAppInstal(s));
			setAppInstalado(true);
		}
		
	}

	public TypeFaceButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		
	}
	
	
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
//		
//		Typeface fonteTexto = Typeface.createFromAsset(this.getContext().getAssets(), "fonts/signikalight.ttf"); 
//		setTypeface(fonteTexto);
	}
	
	
	
	
	private int getResourceAppInstal(String app){
		
		int res = 0;
		
		if("br.com.mobilicidade.bikerec".equals(app)){
			//res = R.drawable.menu_geral_bt_bike_portoleve_instalado_selector;
		}else if("com.mobc.zaerecifeutimate".equals(app)){
			//res = R.drawable.menu_geral_bt_carro_zonaazul_instalado_selector;
		}else{
			//res = R.drawable.menu_geral_bt_carro_portoleve_instalado_selector;
		}
		
		return res;
		
	}

	public boolean isAppInstalado() {
		return appInstalado;
	}

	private void setAppInstalado(boolean appInstalado) {
		this.appInstalado = appInstalado;
	}

	public Intent getItVerificacao() {
		return itVerificacao;
	}

	public void setItVerificacao(Intent itVerificacao) {
		this.itVerificacao = itVerificacao;
	}

}
