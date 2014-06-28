package mobi.rectour.recHoteis.gui;

import java.util.ArrayList;
import java.util.List;

import mobi.rectour.util.FragmentImg;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ViewPagerAdapterFR extends FragmentPagerAdapter   {

	List<FragmentImg> listFr  = new ArrayList<FragmentImg>();
	Context context;
	
	
	String[] titulos = {"O Shopping","A Mobilicidade"};
	int page = 0;
	public ViewPagerAdapterFR(FragmentManager fm,List<FragmentImg> listFr , Context context) {
		super(fm);
		this.listFr = listFr;
		this.context = context;
		
		
	}

	@Override
	public Fragment getItem(int location) {
		
		FragmentImg fr = listFr.get(location);
		ImageView imvFr = new ImageView(context);
		fr.setIvFoto(imvFr);
		
		 Picasso.with(context) //
	        .load(fr.getUrlImg()) //
	        .placeholder( mobi.rectour.R.drawable.hotel_on) //
	        .error(mobi.rectour.R.drawable.hotel_off ) //
	        .fit() //
	        .into(fr.getIvFoto());
		
		return fr;
	}

	@Override
	public int getCount() {
		return listFr.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		
		return titulos[position];
	}
	
	
	
}
