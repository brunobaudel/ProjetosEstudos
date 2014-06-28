package mobi.rectour.util;

import mobi.rectour.R;
import mobi.rectour.web.InformacoesServidor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DialogInformacoesServidor extends DialogFragment implements OnClickListener{
	
	public interface DialogCallBack{
		void btSim(int idDialog);
		void btNao(int idDialog);
	}
	
//	private TextView titulo;
//	private TextView msgUsuario;
	private static String tituloStr = "";
	private static String msgStr = "";
	private static DialogCallBack  dialogCB;
	
	public DialogInformacoesServidor(){
	}
	
	@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
	        //setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent );
		}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.dialog_spg, container, false);
		
		
		((TextView)v.findViewById(R.id.tvTituloDialog)).setText(tituloStr);
		((TextView)v.findViewById(R.id.tvTextoDialog)).setText(msgStr);
		((Button)v.findViewById(R.id.btDialog_Ok)).setOnClickListener(this);
		
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
		
		return v;
	}
	
	public void setDialogCB(DialogCallBack dcb){
		dialogCB =  dcb;
	}
	

	@Override
	public void onClick(View v) {
		dismiss();
		if(dialogCB != null){
			dialogCB.btSim(1);
		}
	}
	
	public static void mostrarDialogInformacaoServidor(FragmentActivity fa,InformacoesServidor inf){
		
		FragmentManager fm = (fa).getSupportFragmentManager();
		tituloStr   = inf.getTituloMsgUsuario();
		msgStr      = inf.getMsgUsuario();
		DialogInformacoesServidor dialogInformacoesServidor = new DialogInformacoesServidor();
		dialogInformacoesServidor.show(fm, "fragment_Inf_Server");
		
	}
	
	public static DialogInformacoesServidor mostrarDialogInformacaoServidor(FragmentActivity fa,InformacoesServidor inf, DialogCallBack  dialogCallBack){
		
		
		
		FragmentManager fm = (fa).getSupportFragmentManager();
		dialogCB = dialogCallBack;
		tituloStr   = inf.getTituloMsgUsuario();
		msgStr      = inf.getMsgUsuario();
		DialogInformacoesServidor dialogInformacoesServidor = new DialogInformacoesServidor();
		dialogInformacoesServidor.show(fm, "fragment_Inf_Server");
		
		return dialogInformacoesServidor;
		
	}
	
}
