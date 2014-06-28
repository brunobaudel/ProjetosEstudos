package mobi.rectour.util;

import mobi.rectour.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FragmentImg extends Fragment {
	
	private ImageView ivFoto;
	private String urlImg;
	
	public FragmentImg(){
		
		
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.hotel_informacoes_tela_fr_imgs, null);
		//ImageView img = //((ImageView) v.findViewById(R.id.ivFoto) )  ;
		
		LinearLayout ll = (LinearLayout) v.findViewById(R.id.llContainer);
		
		ivFoto.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));
		
		ViewGroup parent = (ViewGroup) ivFoto.getParent();
	    if (parent != null){
	        parent.removeView(ivFoto);
	        ll.addView(ivFoto);
	    }else{
	    	ll.addView(ivFoto);
	    }
	    
	    
		return v;
	}


	public ImageView getIvFoto() {
		return ivFoto;
	}

	public void setIvFoto(ImageView ivFoto) {
		this.ivFoto = ivFoto;
	}


	public String getUrlImg() {
		return urlImg;
	}


	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	
}
