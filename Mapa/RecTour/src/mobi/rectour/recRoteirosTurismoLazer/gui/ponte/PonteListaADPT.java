package mobi.rectour.recRoteirosTurismoLazer.gui.ponte;

import mobi.rectour.R;
import mobi.rectour.util.UtilRecTour;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PonteListaADPT extends CursorAdapter{

	public PonteListaADPT(Context context, Cursor cursor) {
		super(context, cursor, true);
	}

	public void bindView(View view, Context context, Cursor cursor) {
		
//		if (cursor.moveToNext()) {

			String rtNome = cursor.getString(cursor.getColumnIndex("rtNome"));
			
			double distancia = cursor.getDouble(cursor.getColumnIndex("distancia"));
			double distanciaKm = UtilRecTour.convertPartialDistanceToKm(distancia);

			String lugar = rtNome + "\n" + UtilRecTour.getLegendaDistancia(distanciaKm) ;
			((TextView) view.findViewById(R.id.tvNome)).setText(lugar);
			
			/*
			((TextView) view.findViewById(R.id.tvNome)).setText(rtNome);
			((TextView) view.findViewById(R.id.tvDistancia)).setText(UtilRecTour.getLegendaDistancia(distanciaKm));
			*/
			
			LatLng pontoMapa = new LatLng( cursor.getDouble(cursor.getColumnIndex("rtLatitude")),cursor.getDouble(cursor.getColumnIndex("rtLongitude")));
		     
		    view.setTag(pontoMapa);
		//}
	}

	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.ponte_lista_item_card, parent, false);
		
		return retView;
	}
	
}
