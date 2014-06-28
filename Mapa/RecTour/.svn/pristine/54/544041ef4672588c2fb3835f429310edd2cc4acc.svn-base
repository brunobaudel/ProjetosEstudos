package mobi.rectour.recHoteis.gui.adapt_listas;

import mobi.rectour.R;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class HotelListaNavegacaoADPT extends CursorAdapter {

	public HotelListaNavegacaoADPT(Context context, Cursor cursor) {
		super(context, cursor, true);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		
			String rtNome = cursor.getString(cursor.getColumnIndex("rtNome"));
			
			((TextView) view.findViewById(R.id.tvNome)).setText(rtNome);
			
			LatLng pontoMapa = new LatLng( cursor.getDouble(cursor.getColumnIndex("rtLatitude")),cursor.getDouble(cursor.getColumnIndex("rtLongitude")));
		     
		    view.setTag(pontoMapa);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.hotel_lista_navegacao_item, parent, false);
		
		return retView;
	}
}
