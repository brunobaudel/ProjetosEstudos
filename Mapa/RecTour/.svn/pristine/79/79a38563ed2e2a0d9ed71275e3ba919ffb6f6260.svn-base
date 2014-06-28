package mobi.rectour.util;

import mobi.rectour.R;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DialogListagem extends DialogFragment implements OnClickListener{
	
	private static BaseAdapter baseAdapter;
	
	private static OnItemClickListener onItemClick;
	
	private static DialogListagem dl;
	
	public DialogListagem(){
		
	}
	
	@Override
	public void onCreate(final Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme);
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.dialog_listagem, null);
		
		((Button)v.findViewById(R.id.btVoltar)).setOnClickListener(this);
		
		baseAdapter.notifyDataSetChanged();
		
		ListView lv = (ListView) v.findViewById(R.id.lvListagem);
		lv.setAdapter(baseAdapter);
		lv.setOnItemClickListener(onItemClick);
		
		
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
		
		return v;
	}
	
	
	public static void showDialog(FragmentActivity fa,BaseAdapter ba, OnItemClickListener onItemClickL){
		
		FragmentManager fm = (fa).getSupportFragmentManager();
		baseAdapter = ba;
		onItemClick = onItemClickL;
		DialogListagem listagem = new DialogListagem();
		dl = listagem;
		listagem.show(fm, "fragment_Listagem");
		
	}

	@Override
	public void onClick(View v) {
		dismiss();
		
	}
	
	
	public static void fecharDialog(){
		
		if(dl != null)
			dl.dismiss();
		
	}
	

}
