package com.br.folhas;


import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ProgressDialogPersonalisadoMOBC extends ProgressDialog {

	/*
	private ImageView folha1;
	private ImageView folha2;
	private ImageView folha3;
	private ImageView folha4;
	
	private AninSeno aninFolha1;
	private AninSeno aninFolha2;
	private AninSeno aninFolha3;
	private AninSeno aninFolha4;
	
	
	private int tamanhoViewWidth;*/
	
	private Context c;
	
	private RelativeLayout rl;
	private ImageView rodaZae;
	
	private ImageView bk;
	private ImageView bk2;
	
	private AninRotacionar aninRotacao;
	private AninDireita aninDireita;
	private AninBks aninBks;
	
	
	private String msgDialog;
	
	private boolean indeterminate;
	private boolean cancelable;
	private OnCancelListener onCancelListener;
	
	public ProgressDialogPersonalisadoMOBC(Context context) {
		super(context);
		indeterminate = false;
		cancelable = false; 
		c = context;
	}
	
	
	@Override
	public void show() {
		//super.show(c,"","",isIndeterminate(),isCancelable(),getOnCancelListener());
		super.show();
		
		setContentView(R.layout.progressdialog_mobc);
		
		rl =  (RelativeLayout)findViewById(R.id.rlDialog);
		
		rodaZae = (ImageView) rl.findViewById(R.telasplash.imgSplash);
		aninRotacao = new AninRotacionar(rodaZae);
		aninRotacao.initAnin();
//		if(msgDialog != null && !msgDialog.equalsIgnoreCase("")){
//			((TextView)rl.findViewById(R.id.tvTexto)).setText(msgDialog);
//		}
		   
		  ViewTreeObserver vto = rl.getViewTreeObserver();
		  vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

		    @Override
		    public void onGlobalLayout() {
		        int tamanhoView = rl.getHeight();
		        int tamanhoViewWidth = rl.getWidth();
		        
		        ViewTreeObserver obs = rl.getViewTreeObserver();
		        obs.removeGlobalOnLayoutListener(this);
		        
		        bk = (ImageView) rl.findViewById(R.id.ivBkRio);
				bk2 = (ImageView) rl.findViewById(R.id.ivBkRio2);
				
				List<ImageView> lstImgViews = new ArrayList<ImageView>();
				lstImgViews.add(bk);
				lstImgViews.add(bk2);
				
				aninBks = new AninBks(lstImgViews);
				aninBks.setWidth(tamanhoViewWidth);
				aninBks.initAnin();
		        
		        //aninDireita = new AninDireita(bk);
				//aninDireita.setWidth(tamanhoViewWidth);
				
				//aninDireita.initAnin();
				 
		    }

		  });
		
	}
	
	public String getMsgDialog() {
		return msgDialog;
	}


	public void setMsgDialog(String msgDialog) {
		this.msgDialog = msgDialog;
	}


	public OnCancelListener getOnCancelListener() {
		return onCancelListener;
	}


	public void setOnCancelListener(OnCancelListener onCancelListener) {
		this.onCancelListener = onCancelListener;
	}
	

}
